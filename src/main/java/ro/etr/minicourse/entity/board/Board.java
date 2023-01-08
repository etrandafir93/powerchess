package ro.etr.minicourse.entity.board;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;
import static ro.etr.minicourse.entity.board.TeamColor.BLACK;
import static ro.etr.minicourse.entity.board.TeamColor.WHITE;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ro.etr.minicourse.entity.board.moves.Move;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Board {
    private final int boardWidth;
    private final int boardHeight;

    @Getter
    private final Map<Square, Piece> board;
    private final TeamColor currentPlayersTurn;

    public static Board chessBoard() {
        return new Board(8, 8, new HashMap<>(), WHITE);
    }

    public Board putPiece(Piece piece, Square square) {
        var newBoard = new HashMap<>(board);
        newBoard.put(square, piece);
        return new Board(boardWidth, boardHeight, newBoard, currentPlayersTurn);
    }

    public Board removePiece(Piece piece, Square square) {
        validatePieceSquare(piece, square);
        var newBoard = new HashMap<>(board);
        newBoard.remove(square);
        return new Board(boardWidth, boardHeight, newBoard, currentPlayersTurn);
    }

    public void validateMove(Piece piece, Square from, Square to) {
        validatePieceSquare(piece, from);
        if (currentPlayersTurn != piece.getTeamColor()) {
            var err = MessageFormat.format("{0} cannot be moved because is {1} players turn", piece, currentPlayersTurn);
            throw new IllegalArgumentException(err);
        }
        if (getPossibleMoves(piece, from).stream().noneMatch(to::equals)) {
            var err = MessageFormat.format("{0} cannot move from {1} to {2}", piece, from, to);
            throw new IllegalArgumentException(err);
        }
    }

    public Board movePiece(Piece piece, Square from, Square to) {
        return removePiece(piece, from)
            .putPiece(piece, to)
            .changePlayersTurn();
    }

    public Set<Square> getPossibleMoves(Piece piece, Square from) {
        return piece.getMovement()
            .getAvailableMoves(from)
            .stream()
            .filter(this::isOnTheBoard)
            .filter(this::isNotObstructed)
            .map(Move::to)
            .collect(toSet());
    }

    private void validatePieceSquare(Piece piece, Square square) {
        var pieceFromBoard = board.getOrDefault(square, null);
        if (!piece.isSamePiece(pieceFromBoard)) {
            throw new IllegalArgumentException(piece + " is not on " + square + "; instead, on the square it was found: " + pieceFromBoard);
        }
    }

    private boolean isNotObstructed(Move move) {
        return move.path()
            .stream()
            .skip(1l)
            .noneMatch(square -> board.containsKey(square));
    }

    private boolean isOnTheBoard(Move move) {
        var square = move.to();
        return square.x() >= 0
            && square.y() >= 0
            && square.x() < boardWidth
            && square.y() < boardHeight;
    }

    private Board changePlayersTurn() {
        TeamColor nextPlayer = currentPlayersTurn == WHITE ? BLACK : WHITE;
        return new Board(boardWidth, boardHeight, board, nextPlayer);
    }
}

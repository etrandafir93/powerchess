package ro.etr.minicourse.entity.board;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ro.etr.minicourse.entity.board.moves.Move;

@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Board {
    private final int boardWidth;
    private final int boardHeight;
    @Getter
    private final Map<Square, Piece> board;

    public static Board chessBoard() {
        return new Board(8, 8, new HashMap<>());
    }

    public Board withPiece(Piece piece, Square square) {
        var newBoard = new HashMap<>(board);
        newBoard.put(square, piece);
        return new Board(boardWidth, boardHeight, newBoard);
    }

    public Set<Square> getPossibleMoves(Piece piece) {
        Square square = getSquareOfThePiece(piece);
        return piece.getMovement()
            .getAvailableMoves(square)
            .stream()
            .filter(this::isOnTheBoard)
            .filter(this::isNotObstructed)
            .map(Move::to)
            .collect(toSet());
    }

    private boolean isNotObstructed(Move move) {
        return move.path()
            .stream()
            .skip(1l)
            .noneMatch(square -> board.containsKey(square));
    }

    private Square getSquareOfThePiece(Piece piece) {
        return board.entrySet()
            .stream()
            .filter(entry -> entry.getValue() == piece)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("The piece is not on the board!"));
    }

    private boolean isOnTheBoard(Move move) {
        var square = move.to();
        return square.x() >= 0
            && square.y() >= 0
            && square.x() < boardWidth
            && square.y() < boardHeight;
    }

    public Optional<Piece> getSquare(Square square) {
        return ofNullable(board.getOrDefault(square, null));
    }
}

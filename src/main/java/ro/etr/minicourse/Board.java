package ro.etr.minicourse;

import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Board {

    private final int boardWidth;
    private final int boardHeight;

    public static Board chessBoard() {
        return new Board(8, 8);
    }

    private final Map<Square, Piece> board = new HashMap<>();

    public void putPiece(Square square, Piece piece) {
        board.put(square, piece);
    }

    public Set<Square> getPossibleMoves(Piece piece) {
        Square square = getSquareOfThePiece(piece);
        return piece.getMovement()
            .getAvailableMoves(square)
            .stream()
            .filter(this::isOnTheBoard)
            .collect(toSet());
    }

    private Square getSquareOfThePiece(Piece piece) {
        return board.entrySet()
            .stream()
            .filter(entry -> entry.getValue() == piece)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("The piece is not on the board!"));
    }

    private boolean isOnTheBoard(Square square) {
        return square.x() >= 0 && square.y() >= 0 && square.x() < boardWidth && square.y() < boardHeight;
    }
}

package ro.etr.minicourse.persistence.gameevents;

import static ro.etr.minicourse.entity.board.Square.square;

import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import ro.etr.minicourse.entity.board.Board;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.TeamColor;
import ro.etr.minicourse.entity.pieces.Bishop;
import ro.etr.minicourse.entity.pieces.BlackPawn;
import ro.etr.minicourse.entity.pieces.King;
import ro.etr.minicourse.entity.pieces.Knight;
import ro.etr.minicourse.entity.pieces.Queen;
import ro.etr.minicourse.entity.pieces.Rook;
import ro.etr.minicourse.entity.pieces.WhitePawn;

public class NewChessGameEvent extends GameEvent {
    public NewChessGameEvent() {
        super(UUID.randomUUID());
    }

    @Override
    public Board apply(Board board) {
        board = putPieces(board, TeamColor.WHITE, "1");
        board = putPawns(board, "2", WhitePawn::new);
        board = putPieces(board, TeamColor.BLACK, "8");
        board = putPawns(board, "7", BlackPawn::new);
        return board;
    }

    private Board putPieces(Board board, TeamColor color, String row) {
        return board.withPiece(new Rook(color), square("a" + row))
            .withPiece(new Knight(color), square("b" + row))
            .withPiece(new Bishop(color), square("c" + row))
            .withPiece(new Queen(color), square("d" + row))
            .withPiece(new King(color), square("e" + row))
            .withPiece(new Bishop(color), square("f" + row))
            .withPiece(new Knight(color), square("g" + row))
            .withPiece(new Rook(color), square("h" + row));
    }

    private Board putPawns(Board board, String row, Supplier<Piece> pawn) {
        for (int i = 0; i < 8; i++) {
            String col = (char) ((int) 'a' + i) + "";
            board = board.withPiece(pawn.get(), square(col + row));
        }
        return board;
    }
}

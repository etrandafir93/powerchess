package ro.etr.minicourse.scenarios.movement;

import static ro.etr.minicourse.entity.board.Square.square;

import org.junit.jupiter.api.Test;

import ro.etr.minicourse.entity.board.Board;
import ro.etr.minicourse.entity.pieces.WhitePawn;

public class MovingPiecesTest {

    @Test
    public void t2() {
        Board board = Board.chessBoard();
        WhitePawn pawn = new WhitePawn();

        board.putPiece(pawn, square("e4"));

//        board.getPieces()
    }


}

package ro.etr.minicourse.entity.pieces;

import ro.etr.minicourse.entity.board.moves.MoveFactory;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.TeamColor;

public class WhitePawn extends Piece {
    private static final int ROW_WHERE_CAN_MOVE_TWICE = 1;

    public WhitePawn() {
        super(TeamColor.WHITE);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> {
            int maxSquaresToMove = square.y() == ROW_WHERE_CAN_MOVE_TWICE ? 2 : 1;
            return MoveFactory.allMoves(square, maxSquaresToMove, MoveFactory.Directions.UP);
        };
    }

    @Override
    public String toString() {
        return "[white Pawn]";
    }
}

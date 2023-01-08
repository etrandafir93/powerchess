package ro.etr.minicourse.entity.pieces;

import static ro.etr.minicourse.entity.board.moves.MoveFactory.Directions.DOWN;

import ro.etr.minicourse.entity.board.moves.MoveFactory;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.TeamColor;

public class BlackPawn extends Piece {
    private static final int ROW_WHERE_CAN_MOVE_TWICE = 6;

    public BlackPawn() {
        super(TeamColor.BLACK);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> {
            int maxSquaresToMove = square.y() == ROW_WHERE_CAN_MOVE_TWICE? 2 : 1;
            return MoveFactory.allMoves(square, maxSquaresToMove, DOWN);
        };
    }
}

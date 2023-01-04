package ro.etr.minicourse.pieces;

import java.util.Set;

import ro.etr.minicourse.Piece;
import ro.etr.minicourse.TeamColor;

public class BlackPawn extends Piece {
    private static final int ROW_WHERE_CAN_MOVE_TWICE = 6;

    public BlackPawn() {
        super(TeamColor.BLACK);
    }
    
    @Override
    public MovementProvider getMovement() {
        return square -> {
            if(square.y() == ROW_WHERE_CAN_MOVE_TWICE) {
                return Set.of(square.down(1), square.down(2));
            }
            return Set.of(square.down(1));
        };
    }
}

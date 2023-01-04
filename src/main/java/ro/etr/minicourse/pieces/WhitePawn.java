package ro.etr.minicourse.pieces;

import java.util.Set;

import ro.etr.minicourse.Piece;
import ro.etr.minicourse.TeamColor;

public class WhitePawn extends Piece {
    private static final int ROW_WHERE_CAN_MOVE_TWICE = 1;

    public WhitePawn() {
        super(TeamColor.WHITE);
    }
    
    @Override
    public MovementProvider getMovement() {
        return square -> {
            if(square.y() == ROW_WHERE_CAN_MOVE_TWICE) {
                return Set.of(square.up(1), square.up(2));
            }
            return Set.of(square.up(1));
        };
    }
}

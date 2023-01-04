package ro.etr.minicourse.pieces;

import java.util.Set;

import ro.etr.minicourse.Piece;
import ro.etr.minicourse.TeamColor;

public class King extends Piece {

    public King(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> Set.of(
            square.up(1),
            square.up(1).left(1),
            square.up(1).right(1),
            square.left(1),
            square.right(1),
            square.down(1),
            square.down(1).left(1),
            square.down(1).right(1)
        );
    }
}


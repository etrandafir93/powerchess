package ro.etr.minicourse.pieces;

import java.util.Set;

import ro.etr.minicourse.Piece;
import ro.etr.minicourse.TeamColor;

public class Knight extends Piece {

    public Knight(TeamColor color) {
        super(color);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> Set.of(
            square.up(2).left(1),
            square.up(2).right(1),
            square.down(2).left(1),
            square.down(2).right(1),
            square.left(2).up(1),
            square.left(2).down(1),
            square.right(2).up(1),
            square.right(2).down(1)
        );
    }

}

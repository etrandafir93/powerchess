package ro.etr.minicourse.entity.pieces;

import java.util.List;
import java.util.stream.Stream;

import ro.etr.minicourse.entity.board.moves.Move;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.TeamColor;

public class Knight extends Piece {

    public Knight(TeamColor color) {
        super(color);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> Stream.of(
            square.up(2).left(1),
            square.up(2).right(1),
            square.down(2).left(1),
            square.down(2).right(1),
            square.left(2).up(1),
            square.left(2).down(1),
            square.right(2).up(1),
            square.right(2).down(1)
        ).map(destination -> new Move(List.of(square, destination)))
        .toList();
    }

}

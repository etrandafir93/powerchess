package ro.etr.minicourse.pieces;

import static java.util.stream.Collectors.toSet;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import ro.etr.minicourse.Piece;
import ro.etr.minicourse.TeamColor;

public class Bishop extends Piece {
    private final int maxSquaresToMove = 20;

    public Bishop(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> IntStream.range(1, maxSquaresToMove)
            .boxed()
            .flatMap(n -> Stream.of(
                square.up(n).left(n),
                square.up(n).right(n),
                square.down(n).left(n),
                square.down(n).right(n)))
            .collect(toSet());
    }

}

package ro.etr.minicourse.pieces;

import static java.util.stream.Collectors.toSet;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import ro.etr.minicourse.Piece;
import ro.etr.minicourse.TeamColor;

public class Queen extends Piece {

    private final int maxSquaresToMove = 20;


    public Queen(TeamColor color) {
        super(color);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> IntStream.range(1, maxSquaresToMove)
            .boxed()
            .flatMap(n -> Stream.of(
                square.up(n),
                square.up(n).left(n),
                square.up(n).right(n),
                square.down(n),
                square.down(n).left(n),
                square.down(n).right(n),
                square.right(n),
                square.left(n)
            ))
            .collect(toSet());
    }
}

package ro.etr.minicourse.pieces;

import static java.util.stream.Collectors.toSet;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import ro.etr.minicourse.Piece;
import ro.etr.minicourse.TeamColor;

public class Rook extends Piece {

    private final int maxSquaresToMove = 20;

    public Rook(TeamColor color) {
        super(color);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> IntStream.range(1, maxSquaresToMove)
            .boxed()
            .flatMap(n -> Stream.of(
                square.left(n),
                square.right(n),
                square.up(n),
                square.down(n)
            ))
            .collect(toSet());
    }
}

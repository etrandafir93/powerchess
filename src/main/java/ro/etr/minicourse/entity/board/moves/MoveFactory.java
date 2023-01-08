package ro.etr.minicourse.entity.board.moves;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ro.etr.minicourse.entity.board.Square;

public class MoveFactory {
    @RequiredArgsConstructor
    public enum Directions {
        UP (Square::up),
        DOWN (Square::down),
        LEFT (Square::left),
        RIGHT (Square::right),
        UP_LEFT (Square::upLeft),
        UP_RIGHT (Square::upRight),
        DOWN_LEFT (Square::downLeft),
        DOWN_RIGHT (Square::downRight);

        @Getter
        private final BiFunction<Square, Integer, Square> pathProvider;
    }

    public static List<Move> allMoves(Square from, int maxNrOfSquaresToMove, Directions... directions) {
        return Arrays.stream(directions)
            .map(direction -> mapContext(direction.getPathProvider(), from))
            .map(pathProvider -> allMoves(maxNrOfSquaresToMove, pathProvider))
            .flatMap(Collection::stream)
            .toList();
    }

    public static List<Move> allMoves(int maxNrOfSquaresToMove, Function<Integer, Square> pathProvider) {
        return IntStream.range(1, maxNrOfSquaresToMove + 2).boxed()
            .flatMap(i -> Stream.of(
                buildMove(i, pathProvider::apply)
            ))
            .filter(move -> move.path().size() > 1)
            .toList();
    }

    public static Move buildMove(int maxNrOfSquares, Function<Integer, Square> pathProvider) {
        return new Move(IntStream.range(0, maxNrOfSquares)
            .boxed()
            .map(pathProvider::apply)
            .toList());
    }

    private static Function<Integer, Square> mapContext(BiFunction<Square, Integer, Square> genericFunction, Square squareInstance) {
        return i -> genericFunction.apply(squareInstance, i);
    }

}

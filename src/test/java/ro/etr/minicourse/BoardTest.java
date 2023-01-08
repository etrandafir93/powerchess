package ro.etr.minicourse;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ro.etr.minicourse.entity.board.Square;

class BoardTest {

    @ParameterizedTest(name = "{0} => ({1}, {2})")
    @CsvSource(value = {
        "a1|0|0", "b1|1|0", "c1|2|0", "d1|3|0",
        "a2|0|1", "b2|1|1", "c2|2|1", "d2|3|1",
        "a3|0|2", "b3|1|2", "c3|2|2", "d3|3|2",
        "a4|0|3", "b4|1|3", "c4|2|3", "d4|3|3",
        "a5|0|4", "b5|1|4", "c5|2|4", "d5|3|4",
        "a6|0|5", "b6|1|5", "c6|2|5", "d6|3|5",
        "a7|0|6", "b7|1|6", "c7|2|6", "d7|3|6",
        "a8|0|7", "b8|1|7", "c8|2|7", "d8|3|7",
        "e1|4|0", "f1|5|0", "g1|6|0", "h1|7|0",
        "e2|4|1", "f2|5|1", "g2|6|1", "h2|7|1",
        "e3|4|2", "f3|5|2", "g3|6|2", "h3|7|2",
        "e4|4|3", "f4|5|3", "g4|6|3", "h4|7|3",
        "e5|4|4", "f5|5|4", "g5|6|4", "h5|7|4",
        "e6|4|5", "f6|5|5", "g6|6|5", "h6|7|5",
        "e7|4|6", "f7|5|6", "g7|6|6", "h7|7|6",
        "e8|4|7", "f8|5|7", "g8|6|7", "h8|7|7",
    }, delimiter = '|')
    void shouldConvertChessNotationToCoordinates(String square, int expectedX, int expectedY) {
        assertThat(Square.square(square))
            .isEqualTo(new Square(expectedX, expectedY));
    }

}
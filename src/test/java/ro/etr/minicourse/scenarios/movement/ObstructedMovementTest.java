package ro.etr.minicourse.scenarios.movement;

import static org.assertj.core.api.Assertions.assertThat;
import static ro.etr.minicourse.entity.board.Square.square;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import ro.etr.minicourse.entity.board.Board;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.Square;
import ro.etr.minicourse.entity.board.TeamColor;
import ro.etr.minicourse.helper.SquareArrayConverter;
import ro.etr.minicourse.entity.pieces.Bishop;
import ro.etr.minicourse.entity.pieces.BlackPawn;
import ro.etr.minicourse.entity.pieces.Knight;
import ro.etr.minicourse.entity.pieces.Queen;
import ro.etr.minicourse.entity.pieces.Rook;
import ro.etr.minicourse.entity.pieces.WhitePawn;

public class ObstructedMovementTest {

    private Board board;
    private Rook blockingPiece;

    @BeforeEach
    void beforeEach() {
        blockingPiece = new Rook(TeamColor.WHITE);
        board = Board.chessBoard().withPiece(blockingPiece, square("e5"));
    }

    @DisplayName("obstructing the Rook")
    @ParameterizedTest(name = "rook {0} !-> {1}")
    @CsvSource(value = {
        "e4|e5,e6,e7,e8",
        "e6|e5,e4,e3,e2,e1",
        "d5|e5,f5,g5,h5",
        "f5|e5,d5,c5,b5,a5",
    }, delimiter = '|')
    void shouldObstructTheRook(
        String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedBlockedMoves) {
        runTest(initialSquare, expectedBlockedMoves, new Rook(TeamColor.WHITE));
    }

    @DisplayName("obstructing the Queen")
    @ParameterizedTest(name = "queen {0} !-> {1}")
    @CsvSource(value = {
        "e4|e5,e6,e7,e8",
        "e6|e5,e4,e3,e2,e1",
        "d5|e5,f5,g5,h5",
        "f5|e5,d5,c5,b5,a5",
        "c3|e5,f6,g7,h8",
        "f4|e5,d6,c7,b8",
        "h8|e5,d4,c3,b2,a1",
    }, delimiter = '|')
    void shouldObstructTheQueen(
        String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedBlockedMoves) {
        runTest(initialSquare, expectedBlockedMoves, new Queen(TeamColor.WHITE));
    }

    @DisplayName("obstructing the White Pawn")
    @ParameterizedTest(name = "white pawn {0} !-> {1}")
    @CsvSource(value = {
        "e4|e5,e6",
    }, delimiter = '|')
    void shouldObstructTheWhitePawn(
        String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedBlockedMoves) {
        runTest(initialSquare, expectedBlockedMoves, new WhitePawn());
    }

    @DisplayName("obstructing the Black Pawn")
    @ParameterizedTest(name = "black pawn {0} !-> {1}")
    @CsvSource(value = {
        "e6|e5,e4",
    }, delimiter = '|')
    void shouldObstructTheBlackPawn(
        String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedBlockedMoves) {
        runTest(initialSquare, expectedBlockedMoves, new BlackPawn());
    }

    @DisplayName("obstructing the Bishop")
    @ParameterizedTest(name = "bishop {0} !-> {1}")
    @CsvSource(value = {
        "c3|e5,f6,g7,h8",
        "f4|e5,d6,c7,b8",
        "h8|e5,d4,c3,b2,a1",
    }, delimiter = '|')
    void shouldObstructTheBishop(
        String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedBlockedMoves) {
        runTest(initialSquare, expectedBlockedMoves, new Bishop(TeamColor.WHITE));
    }

    @DisplayName("do NOT obstruct the Knight")
    @ParameterizedTest(name = "knight {0} -> {1}")
    @CsvSource(value = {
        "e4|g5,g3,f6,f2,d6,d2,c5,c3",
        "e6|d4,g7,f8,c5,d8,c7,f4,g5",
    }, delimiter = '|')
    void shouldNotObstructTheKnight(
            String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedPossibleMoves) {
        Knight knight = new Knight(TeamColor.WHITE);
        board = board.withPiece(knight, square(initialSquare));

        Set<Square> possibleMoves = board.getPossibleMoves(knight);

        assertThat(possibleMoves).containsExactlyInAnyOrderElementsOf(expectedPossibleMoves);
    }

    private void runTest(String initialSquare, List<Square> expectedBlockedMoves, Piece piece) {
        board = board.withPiece(piece, square(initialSquare));

        Set<Square> possibleMoves = board.getPossibleMoves(piece);

        assertThat(possibleMoves)
            .doesNotContainAnyElementsOf(expectedBlockedMoves);
    }

}

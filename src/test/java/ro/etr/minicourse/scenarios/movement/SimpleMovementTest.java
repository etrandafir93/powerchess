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
import ro.etr.minicourse.entity.pieces.King;
import ro.etr.minicourse.entity.pieces.Knight;
import ro.etr.minicourse.entity.pieces.Queen;
import ro.etr.minicourse.entity.pieces.Rook;
import ro.etr.minicourse.entity.pieces.WhitePawn;

public class SimpleMovementTest {

    private Board board;

    @BeforeEach
    void beforeEach() {
        board = Board.chessBoard();
    }

    @DisplayName("move the Knight")
    @ParameterizedTest(name = "knight {0} -> {1}")
    @CsvSource(value = {
        "e4|g5,g3,f6,f2,d6,d2,c5,c3",
        "g6|e7,e5,f8,f4,h8,h4",
        "a7|b5,c6,c8",
        "a1|c2,b3"
    }, delimiter = '|')
    void shouldKnowHowToMoveTheKnight(
            String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedPossibleMoves) {
        runTest(initialSquare, expectedPossibleMoves, new Knight(TeamColor.WHITE));
    }

    @DisplayName("move the King")
    @ParameterizedTest(name = "king {0} -> {1}")
    @CsvSource(value = {
        "e4|e3,e5,f3,f4,f5,d3,d4,d5",
        "a1|a2,b1,b2"
    }, delimiter = '|')
    void shouldKnowHowToMoveTheKing(
            String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedPossibleMoves) {
        runTest(initialSquare, expectedPossibleMoves, new King(TeamColor.WHITE));
    }

    @DisplayName("move the Rook")
    @ParameterizedTest(name = "rook {0} -> {1}")
    @CsvSource(value = {
        "e4|e1,e2,e3,e5,e6,e7,e8,a4,b4,c4,d4,f4,g4,h4",
        "a1|a2,a3,a4,a5,a6,a7,a8,b1,c1,d1,e1,f1,g1,h1"
    }, delimiter = '|')
    void shouldKnowHowToMoveTheRook(
            String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedPossibleMoves) {
        runTest(initialSquare, expectedPossibleMoves, new Rook(TeamColor.WHITE));
    }

    @DisplayName("move the Bishop")
    @ParameterizedTest(name = "bishop {0} -> {1}")
    @CsvSource(value = {
        "d6|e5,e7,a3,f8,b4,c5,c7,b8,h2,g3,f4",
        "a1|b2,c3,d4,e5,f6,g7,h8"
    }, delimiter = '|')
    void shouldKnowHowToMoveTheBishop(
            String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedPossibleMoves) {
        runTest(initialSquare, expectedPossibleMoves, new Bishop(TeamColor.WHITE));
    }

    @DisplayName("move the Queen")
    @ParameterizedTest(name = "queen {0} -> {1}")
    @CsvSource(value = {
        "a1|b2,c3,d4,e5,f6,g7,h8,a2,a3,a4,a5,a6,a7,a8,b1,c1,d1,e1,f1,g1,h1"
    }, delimiter = '|')
    void shouldKnowHowToMoveTheQueen(
          String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedPossibleMoves) {
        runTest(initialSquare, expectedPossibleMoves, new Queen(TeamColor.WHITE));
    }

    @DisplayName("move the White Pawn")
    @ParameterizedTest(name = "white pawn {0} -> {1}")
    @CsvSource(value = {
        "a2|a3,a4",
        "d4|d5"
    }, delimiter = '|')
    void shouldKnowHowToMoveTheWhitePawn(
            String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedPossibleMoves) {
        runTest(initialSquare, expectedPossibleMoves, new WhitePawn());
    }

    @DisplayName("move the Black Pawn")
    @ParameterizedTest(name = "white pawn {0} -> {1}")
    @CsvSource(value = {
        "a7|a6,a5",
        "d4|d3"
    }, delimiter = '|')
    void shouldKnowHowToMoveTheBlackPawn(
        String initialSquare, @ConvertWith(SquareArrayConverter.class) List<Square> expectedPossibleMoves) {
        runTest(initialSquare, expectedPossibleMoves, new BlackPawn());
    }

    private void runTest(String initialSquare, List<Square> expectedPossibleMoves, Piece piece) {
        board = board.withPiece(piece, square(initialSquare));

        Set<Square> possibleMoves = board.getPossibleMoves(piece);

        assertThat(possibleMoves).containsExactlyInAnyOrderElementsOf(expectedPossibleMoves);
    }

}



package ro.etr.minicourse.scenarios.usecase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;
import static ro.etr.minicourse.usecases.presentboard.PresentablePiece.Color.BLACK;
import static ro.etr.minicourse.usecases.presentboard.PresentablePiece.Color.WHITE;
import static ro.etr.minicourse.usecases.presentboard.PresentablePiece.Name.BISHOP;
import static ro.etr.minicourse.usecases.presentboard.PresentablePiece.Name.KING;
import static ro.etr.minicourse.usecases.presentboard.PresentablePiece.Name.KNIGHT;
import static ro.etr.minicourse.usecases.presentboard.PresentablePiece.Name.PAWN;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import ro.etr.minicourse.helper.ChessBoardTestHelpers;
import ro.etr.minicourse.helper.ChessBoardTestHelpers.BoardTestNotation;
import ro.etr.minicourse.usecases.MakeMoveUseCase;
import ro.etr.minicourse.usecases.MakeMoveUseCase.Request;
import ro.etr.minicourse.usecases.NewGameUseCase;

public class MakeMoveUseCaseTest {

    private MakeMoveUseCase usecase = new MakeMoveUseCase();


    @Test
    void shouldMoveTheFirstMove() {
        var theGame = usecase.makeMove(
            new Request(givenANewGame(), WHITE, PAWN, "e2", "e4"));

        var expectedBoard = new BoardTestNotation("""
                r   n   b   q   k   b   n   r
                p   p   p   p   p   p   p   p
                -   -   -   -   -   -   -   -
                -   -   -   -   -   -   -   -
                -   -   -   -   P   -   -   -
                -   -   -   -   -   -   -   -
                P   P   P   P   -   P   P   P
                R   N   B   Q   K   B   N   R
            """);
        ChessBoardTestHelpers.assertBoardIsMatchingExpectation(theGame.board(), expectedBoard);
    }

    @Test
    void shouldMoveTheFirstThreeMoves() {
        String gameId = givenANewGame();

        var theGame = usecase.makeMove(
            new Request(gameId, WHITE, PAWN, "e2", "e4"));
        usecase.makeMove(
            new Request(gameId, BLACK, PAWN, "e7", "e5"));
        theGame = usecase.makeMove(
            new Request(gameId, WHITE, KNIGHT, "g1", "f3"));


        var expectedBoard = new BoardTestNotation("""
                r   n   b   q   k   b   n   r
                p   p   p   p   -   p   p   p
                -   -   -   -   -   -   -   -
                -   -   -   -   p   -   -   -
                -   -   -   -   P   -   -   -
                -   -   -   -   -   N   -   -
                P   P   P   P   -   P   P   P
                R   N   B   Q   K   B   -   R
            """);
        ChessBoardTestHelpers.assertBoardIsMatchingExpectation(theGame.board(), expectedBoard);
    }

    @Test
    void shouldNotAllowMoveIfNoPieceIsThere() {
        ThrowingCallable moveAttempt = () ->
            usecase.makeMove(new Request(givenANewGame(), WHITE, PAWN, "e5", "e6"));

        assertThatThrownBy(moveAttempt)
            .hasMessageContaining("[white Pawn] is not on e5; instead, on the square it was found: null");
    }

    @Test
    void shouldNotAllowMoveIfADifferentPieceIsThere() {
        ThrowingCallable moveAttempt = () ->
            usecase.makeMove(new Request(givenANewGame(), BLACK, BISHOP, "e2", "e3"));

        assertThatThrownBy(moveAttempt)
            .hasMessageContaining("[black Bishop] is not on e2; instead, on the square it was found: [white Pawn]");
    }

    @Test
    void shouldNotAllowMoveIfMoveIsIllegal() {
        ThrowingCallable moveAttempt = () ->
            usecase.makeMove(new Request(givenANewGame(), WHITE, PAWN, "e2", "f6"));

        assertThatThrownBy(moveAttempt)
            .hasMessageContaining("[white Pawn] cannot move from e2 to f6");
    }

    @Test
    void shouldNotAllowMoveIfNotItsTurn() {
        ThrowingCallable moveAttempt = () ->
            usecase.makeMove(new Request(givenANewGame(), BLACK, PAWN, "e7", "e6"));

        assertThatThrownBy(moveAttempt)
            .hasMessageContaining("[black Pawn] cannot be moved because is WHITE players turn");
    }

    @Test
    void shouldNotAllowMoveIfThereIsAFriendlyPieceAtTheDestination() {
        ThrowingCallable moveAttempt = () ->
            usecase.makeMove(new Request(givenANewGame(), WHITE, KING, "e1", "f1"));

        assertThatThrownBy(moveAttempt)
            .hasMessageContaining("[white King] cannot move from e1 to f1");
    }

    @Test
    void shouldNotAllowMoveIfThereIsAEnemyPieceAtTheDestination() {
        fail(); //todo
    }

    private String givenANewGame() {
        var theNewGame = new NewGameUseCase().startNewGame(new NewGameUseCase.Request());
        return theNewGame.gameId();
    }

}

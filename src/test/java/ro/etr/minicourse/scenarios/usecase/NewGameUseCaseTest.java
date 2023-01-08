package ro.etr.minicourse.scenarios.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ro.etr.minicourse.helper.ChessBoardTestHelpers;
import ro.etr.minicourse.helper.ChessBoardTestHelpers.BoardTestNotation;
import ro.etr.minicourse.usecases.NewGameUseCase;

public class NewGameUseCaseTest {

    NewGameUseCase usecase = new NewGameUseCase();

    @Test
    void shouldReturnIdAndBoard() {
        var theNewGame = usecase.startNewGame(new NewGameUseCase.Request());

        assertThat(theNewGame.gameId()).isNotNull();
        assertThat(theNewGame.board()).isNotNull();
        assertThat(theNewGame.board()
            .pieces()).isNotEmpty();
    }

    @Test
    void shouldPlacePiecesInStartingPosition() {
        var theNewGame = usecase.startNewGame(new NewGameUseCase.Request());

        var expectedBoard = new BoardTestNotation("""
                r   n   b   q   k   b   n   r
                p   p   p   p   p   p   p   p
                -   -   -   -   -   -   -   -
                -   -   -   -   -   -   -   -
                -   -   -   -   -   -   -   -
                -   -   -   -   -   -   -   -
                P   P   P   P   P   P   P   P
                R   N   B   Q   K   B   N   R
            """);
        ChessBoardTestHelpers.assertBoardIsMatchingExpectation(theNewGame.board(), expectedBoard);
    }


}

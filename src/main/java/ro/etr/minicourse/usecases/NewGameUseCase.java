package ro.etr.minicourse.usecases;

import java.util.UUID;

import ro.etr.minicourse.entity.board.Board;
import ro.etr.minicourse.persistence.GameEventsGateway;
import ro.etr.minicourse.persistence.gameevents.GameEvent;
import ro.etr.minicourse.persistence.gameevents.NewChessGameEvent;
import ro.etr.minicourse.usecases.presentboard.ChessMapper;
import ro.etr.minicourse.usecases.presentboard.PresentableBoard;

public class NewGameUseCase {

    public Response startNewGame(Request request) {
        UUID newGameId = createNewGame().getGameId();
        var events = GameEventsGateway.instance.get(newGameId);
        var board = GameEvent.recreateBoard(events);
        return new Response(newGameId.toString(), ChessMapper.map(board));
    }

    private NewChessGameEvent createNewGame() {
        NewChessGameEvent newGame = new NewChessGameEvent();
        GameEventsGateway.instance.save(newGame);
        return newGame;
    }

    public record Request() {
    }

    public record Response(
        String gameId,
        PresentableBoard board
    ) {
    }

}

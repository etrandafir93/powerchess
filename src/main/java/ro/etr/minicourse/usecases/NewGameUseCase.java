package ro.etr.minicourse.usecases;

import ro.etr.minicourse.entity.board.Board;
import ro.etr.minicourse.persistence.GameEventsGateway;
import ro.etr.minicourse.persistence.gameevents.GameEvent;
import ro.etr.minicourse.persistence.gameevents.NewChessGameEvent;
import ro.etr.minicourse.usecases.presentboard.BoardMapper;
import ro.etr.minicourse.usecases.presentboard.PresentableBoard;

public class NewGameUseCase {

    public Response startNewGame(Request request) {
        NewChessGameEvent newGame = createNewGame();
        var events = GameEventsGateway.instance.get(newGame.getGameId());

        Board board = Board.chessBoard();
        for (GameEvent event : events) {
            board = event.apply(board);
        }

        String gameId = newGame.getGameId().toString();
        PresentableBoard presentableBoard = BoardMapper.map(board);
        return new Response(gameId, presentableBoard);
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

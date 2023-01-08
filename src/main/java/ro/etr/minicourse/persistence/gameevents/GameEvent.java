package ro.etr.minicourse.persistence.gameevents;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ro.etr.minicourse.entity.board.Board;

@RequiredArgsConstructor
public abstract class GameEvent {
    @Getter
    private final UUID gameId;

    public abstract Board apply(Board board);

    public static Board recreateBoard(List<GameEvent> allEvents) {
        Board board = Board.chessBoard();
        for (GameEvent event : allEvents) {
            board = event.apply(board);
        }
        return board;
    }

}

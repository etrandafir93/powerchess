package ro.etr.minicourse.persistence.gameevents;

import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ro.etr.minicourse.entity.board.Board;

@RequiredArgsConstructor
public abstract class GameEvent {
    @Getter
    private final UUID gameId;

    public abstract Board apply(Board board);

}

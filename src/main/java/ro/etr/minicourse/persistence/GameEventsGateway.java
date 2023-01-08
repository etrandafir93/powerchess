package ro.etr.minicourse.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ro.etr.minicourse.persistence.gameevents.GameEvent;

public class GameEventsGateway {

    public static final GameEventsGateway instance = new GameEventsGateway();

    private final Map<UUID, List<GameEvent>> gameEvents = new HashMap<>();

    public void save(GameEvent event) {
        UUID gameId = event.getGameId();
        if (!gameEvents.containsKey(gameId)) {
            gameEvents.put(gameId, new ArrayList<>());
        }
        gameEvents.get(gameId).add(event);
    }

    public List<GameEvent> get(UUID gameId) {
        return gameEvents.get(gameId);
    }

}

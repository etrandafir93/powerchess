package ro.etr.minicourse;

import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Piece {
    @Getter
    private final TeamColor teamColor;

    public abstract MovementProvider getMovement();

    @FunctionalInterface
    public interface MovementProvider {
        Set<Square> getAvailableMoves(Square currentSquare);
    }
}

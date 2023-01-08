package ro.etr.minicourse.entity.board;

import static java.text.MessageFormat.format;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ro.etr.minicourse.entity.board.moves.Move;

@RequiredArgsConstructor
public abstract class Piece {
    @Getter
    private final TeamColor teamColor;

    public abstract MovementProvider getMovement();

    @FunctionalInterface
    public interface MovementProvider {
        List<Move> getAvailableMoves(Square currentSquare);
    }

    @Override
    public String toString() {
        return format("[{0} {1}]", teamColor.name().toLowerCase(), getClass().getSimpleName());
    }

    public boolean isSamePiece(Piece otherPiece) {
        return otherPiece != null
            && this.getClass() == otherPiece.getClass()
            && teamColor == otherPiece.teamColor;
    }
}

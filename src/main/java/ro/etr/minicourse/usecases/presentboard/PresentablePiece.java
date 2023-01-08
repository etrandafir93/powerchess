package ro.etr.minicourse.usecases.presentboard;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record PresentablePiece(
    @NonNull String square,
    @NonNull Name name,
    @NonNull Color color
) {
    public enum Color {
        WHITE, BLACK
    }

    public enum Name {
        PAWN, ROOK, KNIGHT, BISHOP, KING, QUEEN;
    }
}

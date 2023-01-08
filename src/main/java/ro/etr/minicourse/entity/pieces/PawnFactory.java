package ro.etr.minicourse.entity.pieces;

import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.TeamColor;

public class PawnFactory {
    public static Piece create(TeamColor teamColor) {
        if (teamColor == TeamColor.WHITE) {
            return new WhitePawn();
        }
        return new BlackPawn();
    }
}

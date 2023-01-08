package ro.etr.minicourse.entity.pieces;

import ro.etr.minicourse.entity.board.moves.MoveFactory;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.TeamColor;

public class Queen extends Piece {

    private final int maxSquaresToMove = 20;


    public Queen(TeamColor color) {
        super(color);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> MoveFactory.allMoves(square, maxSquaresToMove, MoveFactory.Directions.values());
    }
}

package ro.etr.minicourse.entity.pieces;

import ro.etr.minicourse.entity.board.moves.MoveFactory;
import ro.etr.minicourse.entity.board.moves.MoveFactory.Directions;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.TeamColor;

public class King extends Piece {

    public King(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> MoveFactory.allMoves(square, 1, Directions.values());
    }
}


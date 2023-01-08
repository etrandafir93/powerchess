package ro.etr.minicourse.entity.pieces;

import static ro.etr.minicourse.entity.board.moves.MoveFactory.Directions.DOWN_LEFT;
import static ro.etr.minicourse.entity.board.moves.MoveFactory.Directions.DOWN_RIGHT;
import static ro.etr.minicourse.entity.board.moves.MoveFactory.Directions.UP_LEFT;
import static ro.etr.minicourse.entity.board.moves.MoveFactory.Directions.UP_RIGHT;

import ro.etr.minicourse.entity.board.moves.MoveFactory;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.TeamColor;

public class Bishop extends Piece {
    private final int maxSquaresToMove = 20;

    public Bishop(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> MoveFactory.allMoves(square, maxSquaresToMove, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }

}

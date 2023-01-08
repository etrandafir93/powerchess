package ro.etr.minicourse.entity.pieces;

import static ro.etr.minicourse.entity.board.moves.MoveFactory.Directions.DOWN;
import static ro.etr.minicourse.entity.board.moves.MoveFactory.Directions.LEFT;
import static ro.etr.minicourse.entity.board.moves.MoveFactory.Directions.RIGHT;
import static ro.etr.minicourse.entity.board.moves.MoveFactory.Directions.UP;

import ro.etr.minicourse.entity.board.moves.MoveFactory;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.TeamColor;

public class Rook extends Piece {

    private final int maxSquaresToMove = 20;

    public Rook(TeamColor color) {
        super(color);
    }

    @Override
    public MovementProvider getMovement() {
        return square -> MoveFactory.allMoves(square, maxSquaresToMove, UP, DOWN, LEFT, RIGHT);
    }

}

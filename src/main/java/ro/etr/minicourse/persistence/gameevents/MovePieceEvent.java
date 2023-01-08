package ro.etr.minicourse.persistence.gameevents;

import java.util.UUID;

import ro.etr.minicourse.entity.board.Board;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.Square;
import ro.etr.minicourse.entity.pieces.WhitePawn;

public class MovePieceEvent extends GameEvent {

    private final Piece piece;
    private final Square from;
    private final Square to;

    public MovePieceEvent(UUID gameId, Piece piece, Square from, Square to) {
        super(gameId);
        this.piece = piece;
        this.from =from;
        this.to = to;
    }

    @Override
    public Board apply(Board board) {
        return board.movePiece(piece, from, to);
    }

}

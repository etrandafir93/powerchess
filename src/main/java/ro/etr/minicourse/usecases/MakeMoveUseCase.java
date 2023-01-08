package ro.etr.minicourse.usecases;

import static ro.etr.minicourse.entity.board.Square.square;

import java.util.UUID;

import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.Square;
import ro.etr.minicourse.persistence.GameEventsGateway;
import ro.etr.minicourse.persistence.gameevents.GameEvent;
import ro.etr.minicourse.persistence.gameevents.MovePieceEvent;
import ro.etr.minicourse.usecases.presentboard.ChessMapper;
import ro.etr.minicourse.usecases.presentboard.PresentableBoard;
import ro.etr.minicourse.usecases.presentboard.PresentablePiece;

public class MakeMoveUseCase {

    public Response makeMove(Request request) {
        var piece = ChessMapper.map(request.getPiece());
        UUID gameId = UUID.fromString(request.gameId);
        movePiece(gameId, piece, square(request.squareFrom), square(request.squareTo));
        var board = GameEvent.recreateBoard(GameEventsGateway.instance.get(gameId));
        return new Response(ChessMapper.map(board));
    }

    private void movePiece(UUID gameId, Piece piece, Square from, Square to) {
        var board = GameEvent.recreateBoard(GameEventsGateway.instance.get(gameId));
        board.validateMove(piece, from, to);
        GameEventsGateway.instance.save(new MovePieceEvent(gameId, piece, from, to));
    }

    public record Request(
        String gameId,
        PresentablePiece.Color color,
        PresentablePiece.Name piece,
        String squareFrom,
        String squareTo
    ) {
         public PresentablePiece getPiece() {
            return new PresentablePiece(squareFrom, piece, color);
        }
    }

    public record Response(
        PresentableBoard board
    ) {
    }

}

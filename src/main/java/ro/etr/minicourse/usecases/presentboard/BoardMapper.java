package ro.etr.minicourse.usecases.presentboard;

import static ro.etr.minicourse.usecases.presentboard.PresentablePiece.Color.BLACK;
import static ro.etr.minicourse.usecases.presentboard.PresentablePiece.Color.WHITE;

import java.util.Map;

import ro.etr.minicourse.entity.board.Board;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.Square;
import ro.etr.minicourse.entity.board.TeamColor;
import ro.etr.minicourse.entity.pieces.Bishop;
import ro.etr.minicourse.entity.pieces.BlackPawn;
import ro.etr.minicourse.entity.pieces.King;
import ro.etr.minicourse.entity.pieces.Knight;
import ro.etr.minicourse.entity.pieces.Queen;
import ro.etr.minicourse.entity.pieces.Rook;
import ro.etr.minicourse.entity.pieces.WhitePawn;

public class BoardMapper {
    public static PresentableBoard map (Board board) {
        var pieces = board.getBoard().entrySet().stream()
            .map(entry -> mapPresentablePiece(entry))
            .toList();
        return new PresentableBoard(pieces);
    }

    private static PresentablePiece mapPresentablePiece(Map.Entry<Square, Piece> entry) {
        return PresentablePiece.builder()
            .name(pieceNameMapping.get(entry.getValue().getClass()))
            .square(entry.getKey().getChessNotation())
            .color(entry.getValue().getTeamColor() == TeamColor.WHITE ? WHITE : BLACK)
            .build();
    }

    private static Map<Class, PresentablePiece.Name> pieceNameMapping = Map.of(
        WhitePawn.class, PresentablePiece.Name.PAWN,
        BlackPawn.class, PresentablePiece.Name.PAWN,
        Rook.class, PresentablePiece.Name.ROOK,
        Knight.class, PresentablePiece.Name.KNIGHT,
        Bishop.class, PresentablePiece.Name.BISHOP,
        Queen.class, PresentablePiece.Name.QUEEN,
        King.class, PresentablePiece.Name.KING
    );

}

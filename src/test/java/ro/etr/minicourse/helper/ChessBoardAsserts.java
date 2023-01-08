package ro.etr.minicourse.helper;

import static java.lang.Character.isUpperCase;
import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;
import static ro.etr.minicourse.entity.board.Square.square;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import ro.etr.minicourse.entity.board.Board;
import ro.etr.minicourse.entity.board.Piece;
import ro.etr.minicourse.entity.board.TeamColor;
import ro.etr.minicourse.entity.pieces.Bishop;
import ro.etr.minicourse.entity.pieces.BlackPawn;
import ro.etr.minicourse.entity.pieces.King;
import ro.etr.minicourse.entity.pieces.Knight;
import ro.etr.minicourse.entity.pieces.Queen;
import ro.etr.minicourse.entity.pieces.Rook;
import ro.etr.minicourse.entity.pieces.WhitePawn;
import ro.etr.minicourse.usecases.presentboard.BoardMapper;
import ro.etr.minicourse.usecases.presentboard.PresentableBoard;
import ro.etr.minicourse.usecases.presentboard.PresentablePiece;

public class ChessBoardAsserts {

    /**
     *           ---- usage example ----
     *
     *    Board b = BoardCustomAsserts.buildBoard("""
     *             r   n   b   q   k   b   n   r
     *             p   p   p   p   p   p   p   p
     *             -   -   -   -   -   -   -   -
     *             -   -   -   -   -   -   -   -
     *             -   -   -   -   -   -   -   -
     *             -   -   -   -   -   -   -   -
     *             P   P   P   P   P   P   P   P
     *             R   N   B   Q   K   B   N   R
     *         """);
     * */

    public record BoardTestNotation(String data) {}


    public static void assertBoardIsMatchingExpectation(PresentableBoard actual, BoardTestNotation expected) {
        assertThat(mepToTestNotation(actual).data)
            .isEqualToIgnoringWhitespace(expected.data); // assert for nice debug message

//        assertThat(actual).isEqualTo(mapToPresentableBoard(expected)); //TODO
    }

    public static PresentableBoard mapToPresentableBoard(BoardTestNotation testData) {
        return BoardMapper.map(buildBoard(testData));
    }

    private static Board buildBoard(BoardTestNotation testData) {
        var board = Board.chessBoard();
        var rows = split(testData.data, "\n");
        for (int i = 0; i < 8; i++) {
            var row = split(rows.get(i), "   ");
            for (int col = 0; col < 8; col++) {
                var d = row.get(col);
                if("-".equals(d)) {
                    continue;
                }
                var sr = 8 - i;
                var sq = (char)('a' + col);
                board = board.withPiece(createPiece(d), square(sq + "" + sr));
            }
        }
        return board;
    }

    private static Piece createPiece(String data) {
        if("p".equals(data) || "P".equals(data)) {
            return pawnMapper.get(data);
        }
        var color = isUpperCase(data.charAt(0)) ? TeamColor.WHITE : TeamColor.BLACK;
        return stringToPieceMapping.get(data.toUpperCase()).apply(color);
    }

    private static Map<String, Function<TeamColor, Piece>> stringToPieceMapping = Map.of(
        "R", Rook::new,
        "N", Knight::new,
        "B", Bishop::new,
        "Q", Queen::new,
        "K", King::new
    );

    private static Map<String, Piece> pawnMapper = Map.of(
        "P", new WhitePawn(),
        "p", new BlackPawn()
    );

    private static List<String> split(String row, String regex) {
        return stream(row.trim()
            .split(regex)).toList();
    }

    public static BoardTestNotation mepToTestNotation(PresentableBoard board) {
        Map<String, PresentablePiece> pieceMap = board.pieces()
            .stream()
            .collect(Collectors.toMap(pp -> pp.square(), pp -> pp));

        StringBuilder sb = new StringBuilder();
        for (int row = 8; row > 0; row--) {
            for (char col = 'a'; col <= 'h' ; col++) {
                var square = col + "" + row;
                if(pieceMap.containsKey(square)) {
                    var p = pieceMap.get(square);
                    String pc = presentablePieceNameMapper.get(p.name());
                    if(p.color() == PresentablePiece.Color.BLACK) {
                        pc = pc.toLowerCase();
                    }
                    sb = sb.append(pc);
                } else {
                    sb = sb.append("-");
                }
                if(col != 'h') {
                    sb = sb.append("   ");
                }
            }
            sb = sb.append("\n");
        }
        return new BoardTestNotation(sb.toString());
    }

    private static Map<PresentablePiece.Name, String> presentablePieceNameMapper = Map.of(
        PresentablePiece.Name.PAWN, "P",
        PresentablePiece.Name.ROOK, "R",
        PresentablePiece.Name.KNIGHT, "N",
        PresentablePiece.Name.BISHOP, "B",
        PresentablePiece.Name.QUEEN, "Q",
        PresentablePiece.Name.KING, "K"
    );
}

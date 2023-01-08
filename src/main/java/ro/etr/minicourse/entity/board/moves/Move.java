package ro.etr.minicourse.entity.board.moves;

import java.util.List;

import ro.etr.minicourse.entity.board.Square;

public record Move(List<Square> path) {
    public Square from() {
        return path.get(0);
    }

    public Square to() {
        return path.get(path.size() - 1);
    }
}

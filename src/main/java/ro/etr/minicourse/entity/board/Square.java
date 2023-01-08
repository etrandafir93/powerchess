package ro.etr.minicourse.entity.board;

public record Square(int x, int y) {

    public static Square square(String chessNotation) {
        if (chessNotation.length() != 2) {
            throw new IllegalArgumentException("chessNotation should be 2 characters long! actual: " + chessNotation);
        }
        int x = (int) chessNotation.toLowerCase()
            .charAt(0) - (int) 'a';
        int y = Integer.parseInt(chessNotation.charAt(1) + "") - 1;
        return new Square(x, y);
    }

    @Override
    public String toString() {
        return getChessNotation();
    }

    public String getChessNotation() {
        String col = (char)(x + (int) 'a') + "";
        String row = (y + 1) + "";
        return col + row;
    }

    public Square up(int squaresToMoveUp) {
        return new Square(x, y + squaresToMoveUp);
    }

    public Square down(int squaresToMoveDown) {
        return new Square(x, y - squaresToMoveDown);
    }

    public Square left(int squaresToMoveLeft) {
        return new Square(x - squaresToMoveLeft, y);
    }

    public Square right(int squaresToMoveRght) {
        return new Square(x + squaresToMoveRght, y);
    }


    public Square upLeft(int squaresToMoveUp) {
        return this.up(squaresToMoveUp).left(squaresToMoveUp);
    }
    public Square upRight(int squaresToMoveUp) {
        return this.up(squaresToMoveUp).right(squaresToMoveUp);
    }
    public Square downLeft(int squaresToMoveUp) {
        return this.down(squaresToMoveUp).left(squaresToMoveUp);
    }
    public Square downRight(int squaresToMoveUp) {
        return this.down(squaresToMoveUp).right(squaresToMoveUp);
    }

}
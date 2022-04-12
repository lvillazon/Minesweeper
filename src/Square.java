public class Square {
    // abstracts a single space on the minefield - can be empty or contain a bomb
    private int x;
    private int y;
    private boolean isBomb;

    public Square(int x, int y, boolean isBomb) {
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getCoords() {
        int[] coords = {x, y};
        return coords;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void placeBomb() {
        isBomb = true;
    }

}

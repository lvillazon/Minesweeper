import java.util.LinkedList;
import java.util.Queue;

public class GameMap {
    // abstracts the view of the minefield based on what the player currently knows
    private final int UNEXPLORED = 9;
    private int[][] explored;  // either UNEXPLORED, or the value of the corresponding minefield square
    private Minefield hidden;
    private final int WIDTH;
    private final int HEIGHT;

    public GameMap(int width, int height, double difficulty) {
        WIDTH = width;
        HEIGHT = height;
        int bombs = (int)Math.round(HEIGHT * WIDTH * difficulty);  // assumes 0 < difficulty < 1
        hidden = new Minefield(WIDTH, HEIGHT, bombs);
        // create initially blank explored map
        explored = new int[WIDTH][HEIGHT];
        for (int i=0; i<WIDTH; i++) {
            for (int j=0; j<HEIGHT; j++) {
                explored[i][j] = UNEXPLORED;
            }
        }
    }

    private boolean unexplored(Square position) {
        return explored[position.getX()][position.getY()] == UNEXPLORED;
    }

    public void stepOn(int x, int y) {
        // reveal the square at x,y and explore outwards 'til we reach squares that might contain bombs
        // update the explored map with the results
        if (hidden.getSquare(x, y).isBomb()) {
            System.out.println("BOOM!");
        } else {
            // perform breadth-first search of surrounding spaces
            Queue<Square> toCheck = new LinkedList<Square>();
            toCheck.add(hidden.getSquare(x, y));
            while (!toCheck.isEmpty()) {
                Square current = toCheck.remove();
                int bombs = hidden.nearbyBombs(current);
                if (unexplored(current) && bombs == 0) {
                    // sdd the surrounding squares to the queue of squares we must check
                    for (Square s: hidden.neighbours(current)) {
                        toCheck.add(s);
                    }
                }
                explored[current.getX()][current.getY()] = bombs;
            }
        }
    }

    public int rows() {
        return HEIGHT;
    }

    public int cols() {
        return WIDTH;
    }

    public int getSquare(int x, int y) {
        return explored[x][y];
    }

    public void display() {
        // dump the currently explored map and the hidden map to the console, for debugging
        for (int i=0; i<HEIGHT; i++) {
            for (int j=0; j<WIDTH; j++) {
                if (explored[j][i] == UNEXPLORED) {
                    System.out.print('.');
                } else {
                    System.out.print(explored[j][i]);
                }
                System.out.print(' ');
            }
            System.out.print("    ");
            for (int j=0; j<WIDTH; j++) {
                if (hidden.getSquare(j,i).isBomb()) {
                    System.out.print('X');
                } else {
                    System.out.print('.');
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }

}

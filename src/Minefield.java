import java.util.ArrayList;
import java.util.Random;

public class Minefield {
    // defines the abstract matrix containing the position of the bombs
    // as well as neighbour information, flags etc
    private final int WIDTH;
    private final int HEIGHT;
    private Square[][] bomb_map;  // -1=bomb, 0-8 indicates a clear space with n adjacent bombs

    public Minefield(int width, int height, int bombs) {
        WIDTH = width;
        HEIGHT = height;

        // initialise empty field
        bomb_map = new Square[WIDTH][HEIGHT];
        for (int i=0; i<WIDTH; i++) {
            for (int j=0; j<HEIGHT; j++) {
                bomb_map[i][j] = new Square(i, j, false);
            }
        }

        // place random bombs
        Random r = new Random();
        for (int i=0; i<bombs; i++) {
            boolean contains = true;  // to force the while loop to begin
            int x = 0;
            int y = 0;
            while (contains == true) {
                // pick a random spot on the map
                x = r.nextInt(WIDTH);
                y = r.nextInt(HEIGHT);
                // see what's there
                contains = bomb_map[x][y].isBomb();
            }
            // place a bomb on this empty spot
            bomb_map[x][y].placeBomb();
        }
    }

    public ArrayList<Square> neighbours(Square position) {
        // returns an arraylist of all the adjacent squares to this one
        ArrayList<Square> neighbourSquares = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        if (x>0 && y>0) {  // top left
            neighbourSquares.add(bomb_map[x-1][y-1]);
        }
        if (y>0) {  // top centre
            neighbourSquares.add(bomb_map[x][y-1]);
        }
        if (x<WIDTH-1 && y>0) {  // top right
            neighbourSquares.add(bomb_map[x+1][y-1]);
        }
        if (x>0) {  // centre left
            neighbourSquares.add(bomb_map[x-1][y]);
        }
        if (x<WIDTH-1) {  // centre right
            neighbourSquares.add(bomb_map[x+1][y]);
        }
        if (x>0 && y<HEIGHT-1) {  // bottom left
            neighbourSquares.add(bomb_map[x-1][y+1]);
        }
        if (y<HEIGHT-1) {  // bottom centre
            neighbourSquares.add(bomb_map[x][y+1]);
        }
        if (x<WIDTH-1 && y<HEIGHT-1) {  // bottom right
            neighbourSquares.add(bomb_map[x+1][y+1]);
        }
        return neighbourSquares;
    }

    public int nearbyBombs(Square position) {
        // returns the number of bombs in the adjacent 8 squares to this one
        int bombCount = 0;
        for (Square neighbouringSquare: neighbours(position)) {
            if (neighbouringSquare.isBomb()) {
                bombCount++;
            }
        }
        return bombCount;
    }

    public Square getSquare(int x, int y) {
        // return contents of this square
        return bomb_map[x][y];
    }

    public Square getSquare(int[] coord) {
        // alternative version that supplies x,y as a tuple
        return bomb_map[coord[0]][coord[1]];
    }

    public void display() {
        // dump the minefield to the console, for debugging
        for (int i=0; i<HEIGHT; i++) {
            for (int j=0; j<WIDTH; j++) {
                if (getSquare(j, i).isBomb()) {
                    System.out.print('X');
                } else {
                    System.out.print(nearbyBombs(getSquare(j, i)));
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}

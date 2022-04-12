import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Minesweeper");
        GameMap map = new GameMap(10,10,0.25);
        map.display();
        GameGUI gui = new GameGUI(800,600);

        // game loop
        Scanner input = new Scanner(System.in);
        boolean finished = false;
        while (!finished) {
            System.out.print("Enter x, y: ");
            String coords = input.nextLine();
            int x = Integer.parseInt(coords.split(",")[0]);
            int y = Integer.parseInt(coords.split(",")[1]);
            map.stepOn(x, y);
            map.display();
        }

    }
}

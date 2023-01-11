import java.util.ArrayList;
import java.util.Random;

public class Player implements Runnable {

    private String name;

    private  boolean move = true;

    private GameBoard playerBoard = new GameBoard();

    private ArrayList<Integer> myMoves = new ArrayList<>();

    public GameBoard getPlayerBoard() {
        return playerBoard;
    }

    public Player(String name) {
        this.name = name;
        for (int i = 16; i != -1; i--) {
            myMoves.add(i);
        }
        System.out.println("Игрок" + name + " готов к игре!");
        playerBoard.start(name);
    }

    public int getMove() {
        int size = myMoves.size();
        int item = -1;
        while (item < 1)
            item = new Random().nextInt(size);
        int myMove = myMoves.get(item); myMoves.remove(item);
        return myMove;
    }

    public String getName() {
        return name;
    }
    public void setMove(boolean move) {
        this.move = move;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (move) {
                    Main.aaaaaa(this);
                }
            }
        } catch (InterruptedException e) {
        }
    }
}

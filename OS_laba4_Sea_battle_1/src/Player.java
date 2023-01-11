import java.util.ArrayList;
import java.util.Random;

public class Player {

    private String name;

    private  boolean move = true;

    private GameBoard playerBoard = new GameBoard();

    private ArrayList<Integer> myMoves = new ArrayList<>();

    public GameBoard getPlayerBoard() {
        return playerBoard;
    }

    public Player(String name) {
        this.name = name;

        for (int i = 15; i !=-1; i--) {
            myMoves.add(i);
        }

        System.out.println("Игрок" + name + " готов к игре!");
        playerBoard.start();
    }

    public int getMove() {
        int size = myMoves.size();
        int item = -1; // In real life, the Random object should be rather more shared than this
        while (item < 1)
            item = new Random().nextInt(size);
        int myMove = myMoves.get(item); myMoves.remove(item);
//        int i = 0;
//        for(var move : myMoves)
//        {
//            if (i == item) {
//                myMove = move;
//                myMoves.remove(item);
//                return myMove;
//            }
//            i++;
//        }
        return myMove;
    }

    public String getName() {
        return name;
    }

    public static synchronized void makeAMove(GameBoard enemyBoard, int point, String name) {

        int igr = Integer.parseInt(name);
        if (igr == 1)
            System.out.println("Ходит Игрок " + 2);
        else
            System.out.println("Ходит Игрок " + 1);

        if (enemyBoard.getAddressLivingTheShip().get(point) != null) {
            enemyBoard.getAddressLivingTheShip().get(point).hitTheShip(point);
        } else {
            enemyBoard.getGraphic()[point] = 'О';
            enemyBoard.drawBoard();
        }
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

}

import java.util.Objects;

public class Main {
    public static Player player1 = new Player("1");
    public static Player player2 = new Player("2");
    static Thread thread = new Thread(player1);
    static Thread thread2 = new Thread(player2);

    static int[] point1 = new int[5];
    static int[] point2 = new int[5];


    public static void main(String[] args) {
        thread.start();
        thread2.start();
    }

    public static void aaaaaa(Player player) throws InterruptedException {
        Thread threadProc = null;
        makeAMove(player.getPlayerBoard(), player.getMove(), player.getName());

        if (Objects.equals(player.getName(), "1")) {
            player1.setMove(false);
            player2.setMove(true);
            threadProc = thread;
        }

        if (Objects.equals(player.getName(), "2")) {
            player2.setMove(false);
            player1.setMove(true);
            threadProc = thread2;
        }
    }

    public synchronized static void makeAMove(GameBoard enemyBoard, int point, String name) {
        if (point1[0] != -1 & Objects.equals(name, "1")) {
            for (int i = 1; i < 5; i++)
                if (point1[i] > -1 & point1[i] < 16) {
                    point = point1[i];
                    point1[i] = -1;
                    break;
                }
        }
        if (point2[0] != -1 & Objects.equals(name, "2")) {
            for (int i = 1; i < 5; i++)
                if (point2[i] > -1 & point2[i] < 16) {
                    point = point2[i];
                    point2[i] = -1;
                    break;
                }
        }

        int igr = Integer.parseInt(name);
        if (igr == 1) {
            System.out.println("Ходит Игрок " + 2);
        }
        else {
            System.out.println("Ходит Игрок " + 1);
        }
        if (enemyBoard.getLivingTheShip() == 0) {
            if (igr == 1) {
                System.out.println("Проиграл " + 2);
            }
            else {
                System.out.println("Проиграл " + 1);
            }
            System.exit (1);
        } else {
            if (enemyBoard.getAddressLivingTheShip().get(point) != null) {
                enemyBoard.getAddressLivingTheShip().get(point).hitTheShip(point, name);
                if (name == "1") {
                    point1[0] = point;
                    point1[1] = point + 4;
                    point1[2] = point - 4;
                    point1[3] = point - 1;
                    point1[4] = point + 1;
                }
                else {
                    point2[0] = point;
                    point2[1] = point + 4;
                    point2[2] = point - 4;
                    point2[3] = point - 1;
                    point2[4] = point + 1;
                }
            } else if (enemyBoard.getAddressLivingTheShip().isEmpty()) {
                System.out.println("Шагов больше нет!!!");
                System.exit (1);
            } else {
                enemyBoard.getGraphic()[point] = 'О';
                enemyBoard.drawBoard(name);
                int k = 0;
                if (name.equals("1")) {
                    for (int i = 1; i < 5; i++)
                        if (point1[i] > 0 & point2[i] < 16)
                            k++;
                    if (k == 0)
                        point1[0] = -1;
                }
                else {
                    for (int i = 1; i < 5; i++)
                        if (point2[i] > 0 & point2[i] < 16)
                            k++;
                    if (k == 0)
                        point2[0] = -1;
                }
            }
        }

        if (enemyBoard.getLivingTheShip() == 0) {
            System.out.println("Проиграл " + name);
            System.exit (1);
        }
    }
}
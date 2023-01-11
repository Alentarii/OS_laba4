import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket("localhost", 4004);
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                Player player1 = new Player("1");
                Player player2 = new Player("2");

                while (true) {
                    //System.out.println("Я походил");
                    //makeAMove(player1.getPlayerBoard(), player1.getMove(), player1.getName());
                    //out.write("Я походил ходил!Ходи!" + "\n");
                    //out.flush();
                    //String serverWord = in.readLine();

                    makeAMove(player1.getPlayerBoard(), player1.getMove(), player1.getName());
                    makeAMove(player2.getPlayerBoard(), player2.getMove(), player2.getName());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public synchronized static void makeAMove(GameBoard enemyBoard, int point, String name) throws InterruptedException {

        int igr = Integer.parseInt(name);
        if (igr == 1)
            System.out.println("Ходит Игрок " + 2);
        else
            System.out.println("Ходит Игрок " + 1);
        if (enemyBoard.getLivingTheShip() == 0) {
            System.out.println("Проиграл " + name);
            System.exit (1);
        } else {
            if (enemyBoard.getAddressLivingTheShip().get(point) != null) {
                enemyBoard.getAddressLivingTheShip().get(point).hitTheShip(point);
            } else if (enemyBoard.getAddressLivingTheShip().isEmpty()) {
                System.out.println("Шагов больше нет!!!");
            } else {
                enemyBoard.getGraphic()[point] = 'O';
                enemyBoard.drawBoard();
            }
        }

        if (enemyBoard.getLivingTheShip() == 0) {
            System.out.println("Проиграл " + name);
            System.exit (1);
        }
    }
}
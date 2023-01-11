import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Server {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4004); // серверсокет прослушивает порт 4004
                System.out.println("Сервер запущен!"); // хорошо бы серверу
                    clientSocket = server.accept();
                    try {
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        Player player2 = new Player("2");

//                        while (true) {
//                            String path = in.readLine();
//                            makeAMove(player2.getPlayerBoard(), player2.getMove(), player2.getName());
//                            out.write("Привет, это Сервер! Подтверждаю, пути приняты!" + "\n");
//                            out.flush();
//                        }
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
                    } finally { // в любом случае сокет будет закрыт
                        clientSocket.close();
                        // потоки тоже хорошо бы закрыть
                        in.close();
                        out.close();
                    }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
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
            System.out.println("Игрок проиграл " + name);
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
            System.out.println("Игрок проиграл " + name);
            System.exit (1);
        }
    }
}
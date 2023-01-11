import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Server {

    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4004);
                System.out.println("Сервер запущен!");
                while (true) {
                    clientSocket = server.accept();
                    try {
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        String choose = in.readLine();
                        System.out.println(choose);

                        String massage = in.readLine();
                        System.out.println(massage);

                        String offset = in.readLine();
                        System.out.println(offset);

                        String massageCipher;

                        if (Integer.parseInt(choose) == 1) {
                              massageCipher = CaesarCipher.cipher(massage, Integer.parseInt(offset));
                        } else {
                              massageCipher = CaesarCipher.decipher(massage, Integer.parseInt(offset));
                        }

                        out.write("Привет, это Сервер! Ваше сообщение: " + massageCipher);
                        out.flush();
                    } finally {
                        clientSocket.close();
                        in.close();
                        out.close();
                    }
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
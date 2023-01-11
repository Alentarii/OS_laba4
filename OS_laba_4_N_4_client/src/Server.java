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

                        String path = in.readLine();
                        System.out.println(path);

                        String pathTo = in.readLine();
                        System.out.println(pathTo);

                        copyFile(new File(path), new File(pathTo));

                        out.write("Привет, это Сервер! Подтверждаю, пути приняты!");
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

    private static void copyFile(File source, File dest) throws IOException {
        Files.copy(source.toPath(), Path.of(dest.toPath() + "/" + source.getName()));
    }
}
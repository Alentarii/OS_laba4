import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 4004);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Введите путь к файлу, который нужно скопировать:");
                String path = reader.readLine();
                out.write(path + "\n");
                out.flush();
                System.out.println("Введите куда скопировать файл:");
                String pathTo = reader.readLine();
                out.write(pathTo + "\n");
                out.flush();

                String serverWord = in.readLine();
                System.out.println(serverWord);
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
}

//"D:\OSLABA_4\OS_laba_4_N_4_client\copyFile\file"
//"D:\OSLABA_4\OS_laba_4_N_4_client\copyFile\file1"

//D:\OSLABA_4\OS_laba_4_N_4_client
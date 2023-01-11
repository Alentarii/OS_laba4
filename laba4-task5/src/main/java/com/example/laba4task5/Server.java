package com.example.laba4task5;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static BufferedReader in;
    private static BufferedWriter out;

    public Server() {
        lol();
    }

    public static void createFabricServer() {
        ExecutorService executeIt = Executors.newFixedThreadPool(2);

        try (ServerSocket server = new ServerSocket(4004)) {
            System.out.println("Сервер запущен!");

            while (!server.isClosed()) {

                Socket client = server.accept();

                executeIt.execute(new MonoThreadClientHandler(client));
                System.out.println("Соединение установленно.");
            }

            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lol () {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                createFabricServer();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void drawPicture(String param) {
        String[] parameters = param.split(" ");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                HelloApplication.paintFigur(parameters);
            }
        });
    }

    public static class MonoThreadClientHandler implements Runnable {

        private static Socket clientDialog;

        public MonoThreadClientHandler(Socket client) {
            MonoThreadClientHandler.clientDialog = client;
        }

        @Override
        public void run() {
            try {
                try {
                        try {
                            in = new BufferedReader(new InputStreamReader(clientDialog.getInputStream()));
                            out = new BufferedWriter(new OutputStreamWriter(clientDialog.getOutputStream()));

                            String path = in.readLine();
                            drawPicture(path);

                            out.write("Привет, это Сервер! Готово!");
                            out.flush();
                        } finally {
                            clientDialog.close();
                            in.close();
                            out.close();
                        }
                } finally {
                    System.out.println("Клиент отключился!");
                    clientDialog.close();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

}

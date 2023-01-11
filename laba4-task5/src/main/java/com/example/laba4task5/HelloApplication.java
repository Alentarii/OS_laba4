package com.example.laba4task5;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;


import java.io.*;

public class HelloApplication extends Application {
    public static Group root = new Group();

    private static Server server = new Server();

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(root, 600, 300);
        stage.setTitle("Task #5");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

    public static void paintFigur(String[] param){
        Polygon myshape = new Polygon(); //300 50 450 150 300 250 150 150
        myshape.getPoints().addAll(Double.valueOf(param[0]), Double.valueOf(param[1]),
                Double.valueOf(param[2]),
                Double.valueOf(param[3]),
                Double.valueOf(param[4]),
                Double.valueOf(param[5]),
                Double.valueOf(param[6]));

        Color color;

        switch (param[3]) {
            case  ("1"):
                color = Color.AQUA;
                break;
            case ("2"):
                color = Color.AQUAMARINE;
                break;
            case ("3"):
                color = Color.BLACK;
                break;
            case ("4"):
                color = Color.BROWN;
                break;
            case ("5"):
                color = Color.BLUEVIOLET;
                break;
            case ("6"):
                color = Color.TURQUOISE;
                break;
            default:
                color = Color.TAN;
                break;
        }

        myshape.setFill(color);
        myshape.setStroke(Paint.valueOf("#00000080"));

        root.getChildren().add(myshape);
    }

    public static int rnd(final double max)
    {
        return (int) (Math.random() * max);
    }
}
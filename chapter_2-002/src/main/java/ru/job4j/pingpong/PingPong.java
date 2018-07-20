package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Simple JavaFX application with moving little rectangle. The little rectangle moves in it's own thread.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 20/07/2018
 */
public class PingPong extends Application {
    /**
     * The window title.
     */
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    /**
     * Main thread.
     *
     * @param primaryStage - the main rectangle stage for moving little rectangle.
     * @throws Exception - any possible exceptions.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        new Thread(new RectangleMove(rect)).start();
        primaryStage.setScene(new Scene(group, limitX, limitY));
        primaryStage.setTitle(PingPong.JOB4J);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
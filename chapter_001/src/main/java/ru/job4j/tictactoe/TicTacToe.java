package ru.job4j.tictactoe;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Visual component.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 08/05/2018
 */
public class TicTacToe extends Application {
    private static final String JOB4J = "Крестики-нолики www.job4j.ru";
    private final int size = 3;
    private final Figure3T[][] cells = new Figure3T[this.size][this.size];
    private final Logic3T logic = new Logic3T(this.cells);

    /**
     * True if last mark was X, false either. Uses to identify whose turn is now X or O.
     */
    private boolean lastMarkX = false;

    /**
     * True if last mark was O, false either. Uses to identify whose turn is now X or O.
     */
    private boolean lastMarkO = false;

    private Figure3T buildRectangle(int x, int y, int size) {
        Figure3T rect = new Figure3T();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    /**
     * Builds 'X'-figure.
     *
     * @param x - coordinate of 'X'-figure.
     * @param y - coordinate of 'X'-figure.
     * @param size - of 'X'-figure.
     * @return new 'X'-figure.
     */
    private Group buildMarkX(double x, double y, int size) {
        Group group = new Group();
        group.getChildren().addAll(
                new Line(x + 10, y + 10, x + size - 10, y + size - 10),
                new Line(x + size - 10, y + 10, x + 10, y + size - 10)
        );
        return group;
    }

    /**
     * Builds 'O'-figure.
     *
     * @param x - coordinate of 'O'-figure.
     * @param y - coordinate of 'O'-figure.
     * @param size - of 'O'-figure.
     * @return new 'O'-figure.
     */
    private Group buildMarkO(double x, double y, int size) {
        Group group = new Group();
        int radius = size / 2;
        Circle circle = new Circle(x + radius, y + radius, radius - 10);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        group.getChildren().addAll(circle);
        return group;
    }

    private EventHandler<MouseEvent> buildMouseEvent(Group panel) {
        return event -> {
            Figure3T rect = (Figure3T) event.getTarget();
            if (this.checkState()) {
                if (!this.lastMarkX && !this.lastMarkO && event.getButton() == MouseButton.PRIMARY) {
                    rect.take(true);
                    panel.getChildren().add(this.buildMarkX(rect.getX(), rect.getY(), 50));
                    this.lastMarkX = true;
                    this.lastMarkO = false;
                } else if (!this.lastMarkX && !this.lastMarkO && event.getButton() == MouseButton.SECONDARY) {
                    rect.take(false);
                    panel.getChildren().add(this.buildMarkO(rect.getX(), rect.getY(), 50));
                    this.lastMarkX = false;
                    this.lastMarkO = true;
                } else if (!this.lastMarkX && this.lastMarkO && event.getButton() == MouseButton.PRIMARY) {
                    rect.take(true);
                    panel.getChildren().add(this.buildMarkX(rect.getX(), rect.getY(), 50));
                    this.lastMarkX = true;
                    this.lastMarkO = false;
                } else if (this.lastMarkX && !this.lastMarkO && event.getButton() == MouseButton.SECONDARY) {
                    rect.take(false);
                    panel.getChildren().add(this.buildMarkO(rect.getX(), rect.getY(), 50));
                    this.lastMarkX = false;
                    this.lastMarkO = true;
                }
            }
            this.checkWinner();
            this.checkState();
        };
    }

    /**
     * Shows alert message.
     *
     * @param message - the text of alert message.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(TicTacToe.JOB4J);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Checks playing field for empty cells. If there are not empty fields, initialize alert message.
     *
     * @return - true if there is available cells.
     */
    private boolean checkState() {
        boolean gap = this.logic.hasGap();
        if (!gap) {
            this.showAlert("Все поля заполнены! Начните новую игру!");
        }
        return gap;
    }

    /**
     * Checks for winner: 'O' or 'X' and initialize alert message.
     */
    private void checkWinner() {
        if (this.logic.isWinnerX()) {
            this.showAlert("Победили крестики! Начните новую игру");
        } else if (this.logic.isWinnerO()) {
            this.showAlert("Победили нолики! Начните новую игру");
        }
    }

    /**
     * Builds playing field (table 3x3).
     *
     * @return - the playing field.
     */
    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                Figure3T rect = this.buildRectangle(x, y, 50);
                this.cells[x][y] = rect;
                panel.getChildren().add(rect);
                rect.setOnMouseClicked(this.buildMouseEvent(panel));
            }
        }
        return panel;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40.0);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("Начать");
        start.setOnMouseClicked(event -> border.setCenter(this.buildGrid()));
        control.getChildren().addAll(start);
        border.setBottom(control);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border, 300, 300));
        stage.setTitle(TicTacToe.JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}
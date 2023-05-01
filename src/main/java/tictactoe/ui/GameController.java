package tictactoe.ui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import tictactoe.model.TicTacToeState;

public class GameController {
    @FXML
    private GridPane board;
    @FXML
    private Button startButton;
    @FXML
    private Button finishButton;
    private BooleanProperty inGame;
    private ObjectProperty<TicTacToeState> model;

    @FXML
    private void initialize() {
        inGame = new SimpleBooleanProperty(false);
        model = new SimpleObjectProperty<>(new TicTacToeState());

        model.addListener((event, old, actual) -> {
            drawGrid();
        });

        inGame.addListener((event, old, actual) -> {
            startButton.setDisable(actual);
            finishButton.setDisable(!actual);
        });

        drawGrid();
        finishButton.setDisable(true);
    }

    @FXML
    private void onStartGame() {
        inGame.set(true);
        model.set(new TicTacToeState());
    }

    @FXML
    private void onFinishGame() {
        inGame.set(false);
        model.set(new TicTacToeState());
    }

    private void drawGrid() {
    }
}

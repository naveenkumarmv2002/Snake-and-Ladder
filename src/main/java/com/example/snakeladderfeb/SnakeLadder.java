package com.example.snakeladderfeb;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tileSize=40, width=10, height=10;
    public static final int buttonLine = height*tileSize +50 , infoLine = buttonLine - 30;

    private static Dice dice =new Dice();

    private Player playerOne, playerTwo;

    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize( width*tileSize, height*tileSize +100);

        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        Image img = new Image("F:\\project\\snakeLadderfeb\\src\\main\\360_F_601024377_wAmzzfFaHPIBhcaIVJaLUgC3jkjQh7aU.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        Button playerOneButton  = new Button("player One");
        Button playerTwoButtton = new Button("Player Two");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButtton.setTranslateY(buttonLine);
        playerTwoButtton.setTranslateX(300);
        playerTwoButtton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(160);


        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start the Game");

        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(300);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);


        playerOne = new Player(tileSize, Color.BLACK, "Naveen");
        playerTwo = new Player(tileSize-5, Color.WHITE, "Vijay");

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue =dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : "+ diceValue);
                        playerOne.movePlayer(diceValue);

                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is " + playerOne.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoTurn = true;
                            playerTwoButtton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted=false;

                        }
                        else {
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButtton.setDisable(false);
                            playerTwoLabel.setText("Your Turn " + playerTwo.getName());

                        }


                    }
                }

            }
        });

        playerTwoButtton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue =dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : "+ diceValue);
                        playerTwo.movePlayer(diceValue);

                        if(playerTwo.isWinner()){
                            diceLabel.setText("Winner is " + playerTwo.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoTurn = true;
                            playerTwoButtton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");

                        }
                        else {
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn " + playerOne.getName());

                            playerTwoTurn = false;
                            playerTwoButtton.setDisable(true);
                            playerTwoLabel.setText("");
                        }

                    }
                }

            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn = true;
                playerOneLabel.setText("Your Turn " + playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();


                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButtton.setDisable(true);
                playerTwo.startingPosition();
            }
        });

        root.getChildren().addAll(board ,
                playerTwoButtton, playerOneButton , startButton,
                playerOneLabel,playerTwoLabel,diceLabel,
                playerOne.getCoin(), playerTwo.getCoin()

        );

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
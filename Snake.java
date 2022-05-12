package com.example.snake;
//package com.example.sankepersoanalwork;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class advancedSnake extends Application {
    static int speed = 6;
    static int foodcolor = 0;
    static int width = 20;
    static int height = 20;
    static int foodX = 0;
    static int foodY = 0;
    static int cornersize = 25;
    static List<Corner> snake = new ArrayList<>();
    static Dir direction = Dir.left;
    static boolean gameOver = false;
    static Random rand = new Random();
//    Second
    static int speed2 = 6;
    static int foodX2 = 0;
    static int foodY2 = 0;
    static List<Corner> snake2 = new ArrayList<>();
    static boolean gameOver2 = false;
    static int foodcolor2 = 0;


    public enum Dir {
        left, right, up, down
    }

    public static class Corner {
        int x;
        int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public void start(Stage primaryStage) {
//        First Window
//        controllers
        Button btnStart = new Button(" Start ");
        Button btnEnd = new Button(" Exit ");
//       btn style
        btnStart.setMaxWidth(250);
        btnEnd.setMaxWidth(250);
        btnStart.setStyle("-fx-background-color: #c2185b; ");
        btnEnd.setStyle("-fx-background-color: #c2185b; ");
        Font font = new Font(20);
        btnStart.setFont(font);
        btnEnd.setFont(font);
        //        layout
        VBox box = new VBox();
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(btnStart, btnEnd);
        box.setStyle("-fx-background-color: BLACK;");
        Scene scene = new Scene(box, 800, 400);
//        Scene Style
        scene.setFill(Color.BLACK);
//        Stage style
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("Start Window");
//      To Launch
        primaryStage.setScene(scene);
        primaryStage.show();
//       End First Window
        btnEnd.setOnAction(ActionEvent -> {
            primaryStage.close();
        });
//       Second Window
        btnStart.setOnAction(ActionEvent -> {
            //        controllers
            primaryStage.close();
            Button playWithBorder = new Button(" Play With Solid Border  ");
            Button playWithNoBorder = new Button(" Play With No Border ");
            Button returnto = new Button(" Return ");
            Button exit = new Button("   Exit  ");
//          btn style
            playWithBorder.setMaxWidth(250);
            playWithNoBorder.setMaxWidth(250);
            exit.setMaxWidth(250);
            returnto.setMaxWidth(250);

            playWithBorder.setStyle("-fx-background-color: #c2185b; ");
            playWithNoBorder.setStyle("-fx-background-color: #c2185b; ");
            exit.setStyle("-fx-background-color:#c2185b");
            returnto.setStyle("-fx-background-color:#c2185b");

            Font font2 = new Font(20);
            playWithBorder.setFont(font2);
            playWithNoBorder.setFont(font2);
            exit.setFont(font2);
            returnto.setFont(font2);

            //  layout
            VBox box2 = new VBox();
            box2.setSpacing(20);
            box2.setAlignment(Pos.CENTER);
            box2.getChildren().addAll(playWithBorder, playWithNoBorder, returnto, exit);
            box2.setStyle("-fx-background-color: BLACK;");
            Scene scene2 = new Scene(box2, 800, 400);
//           Scene Style
            scene2.setFill(Color.BLACK);
//          Stage style
            Stage primaryStage2 = new Stage();
            primaryStage2.initStyle(StageStyle.UTILITY);
            primaryStage2.setTitle("Start Window");
//          to Launch
            primaryStage2.setScene(scene2);
            primaryStage2.show();
//           End First Window
//            Stage stage2 = new Stage();
            primaryStage2.setTitle("Choose What You want");
            primaryStage2.show();
            exit.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent ActionEvent) {
                    primaryStage2.close();
                }
            });
            returnto.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent ActionEvent) {
                    primaryStage.show();
                    primaryStage2.close();
                }
            });

//            Main java fx

            playWithNoBorder.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent ActionEvent) {

                    newFood();
                    VBox root = new VBox();
                    Canvas c = new Canvas(width * cornersize, height * cornersize);
                    GraphicsContext gc = c.getGraphicsContext2D();
                    root.getChildren().add(c);
                    new AnimationTimer() {
                        long lastTick = 0;

                        public void handle(long now) {
                            if (lastTick == 0) {
                                lastTick = now;
                                Tick1(gc);
                                return;
                            }
                            if (now - lastTick > 1000000000 / speed) {
                                lastTick = now;
                                Tick1(gc);
                            }
                        }

                    }.start();

                    Scene scene = new Scene(root, width * cornersize, height * cornersize);

                    // control
                    scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                        if (key.getCode() == KeyCode.UP) {
                            direction = Dir.up;
                        }
                        if (key.getCode() == KeyCode.LEFT) {
                            direction = Dir.left;
                        }
                        if (key.getCode() == KeyCode.DOWN) {
                            direction = Dir.down;
                        }
                        if (key.getCode() == KeyCode.RIGHT) {
                            direction = Dir.right;
                        }
                    });
                    // Body Of Snake When Start Play
                    snake.add(new Corner(width / 2, height / 2));
                    snake.add(new Corner(width / 2, height / 2));
                    snake.add(new Corner(width / 2, height / 2));
                    Stage primaryStage3 = new Stage();
                    primaryStage3.setScene(scene);
                    primaryStage3.setTitle("SNAKE GAME");
                    primaryStage3.show();


                }
            });
//            Play Two
            playWithBorder.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent ActionEvent) {
                    newFood2();
                    VBox root = new VBox();
                    Canvas c = new Canvas(width * cornersize, height * cornersize);
                    GraphicsContext gc = c.getGraphicsContext2D();
                    root.getChildren().add(c);
                    new AnimationTimer() {
                        long lastTick = 0;
                        public void handle(long now) {
                            if (lastTick == 0) {
                                lastTick = now;
                                tick(gc);
                                return;
                            }
                            if (now - lastTick > 1000000000 / speed2) {
                                lastTick = now;
                                tick(gc);
                            }
                        }

                    }.start();

                    Scene scene = new Scene(root, width * cornersize, height * cornersize);

                    // control
                    scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                        if (key.getCode() == KeyCode.UP) {
                            direction = Dir.up;
                        }
                        if (key.getCode() == KeyCode.LEFT) {
                            direction = Dir.left;
                        }
                        if (key.getCode() == KeyCode.DOWN) {
                            direction = Dir.down;
                        }
                        if (key.getCode() == KeyCode.RIGHT) {
                            direction = Dir.right;
                        }

                    });


                    // Body Of Snake When Start Play
                    snake2.add(new Corner(width / 2, height / 2));
                    snake2.add(new Corner(width / 2, height / 2));
                    snake2.add(new Corner(width / 2, height / 2));
                    //If you do not want to use css style, you can just delete the next line.
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    Stage primaryStage8=new Stage();
                    primaryStage8.setScene(scene);
                    primaryStage8.setTitle("SNAKE GAME");
                    primaryStage8.show();


                }
            });










//
        });


}
//Main Function One
    public static void Tick1(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            return ;
        }
//        Make Snake Body Move With other
        for (int i = snake.toArray().length - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction) {
            case up:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    snake.get(0).y=height;
//                    gameOver = true;
                }
                break;
            case down:
                snake.get(0).y++;
                if (snake.get(0).y > height) {
                    snake.get(0).y=0;
                }
                break;
            case left:
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    snake.get(0).x=width;
                }
                break;
            case right:
                snake.get(0).x++;
                if (snake.get(0).x > width) {
                    snake.get(0).x=0;
                }
                break;
        }
        // eat food
        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Corner( -1,-1));
            newFood();
        }
//        self destroy When any block of snake touch other
//        Prevent Moving in reverse Direction
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
            }
        }

        // fill
        // background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * cornersize, height * cornersize);

        // score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font( 30));
        gc.fillText("Your Score: " + (speed - 6), 10, 30);
        // random foodColor
//        Initial color
        Color foodColor = Color.WHITE;
        switch (foodcolor) {
            case 0:
                foodColor = Color.DEEPPINK;
                break;
            case 1:
                foodColor = Color.CRIMSON;
                break;
            case 2:
                foodColor = Color.TOMATO;
                break;
            case 3:
                foodColor = Color.GOLD;
                break;
        }
//        Change Color After Each Eat
        gc.setFill(foodColor);
//        To Fill Color For Food
        gc.fillOval(foodX * cornersize, foodY * cornersize, cornersize, cornersize);
        // Snake Body
        for (Corner c : snake) {
//            If We Delete this line the snake body will change according to food
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 1, cornersize - 1);
        }
    }
    // food
    public static void newFood() {
        while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);
            foodcolor = rand.nextInt(4);
            speed++;
            break;

        }
    }
//Main Function Two
public static void tick(GraphicsContext gc) {
    if (gameOver2) {
        gc.setFill(Color.RED);
        gc.setFont(new Font("", 50));
        gc.fillText("GAME OVER", 100, 250);
        return ;
    }
//        Make Snake Body Move With other
    for (int i = snake2.toArray().length - 1; i >= 1; i--) {
        snake2.get(i).x = snake2.get(i - 1).x;
        snake2.get(i).y = snake2.get(i - 1).y;
    }

    switch (direction) {
        case up:
            snake2.get(0).y--;
            if (snake2.get(0).y < 0) {
                gameOver2=true;
//                    gameOver = true;
            }
            break;
        case down:
            snake2.get(0).y++;
            if (snake2.get(0).y > height) {
                gameOver2=true;
            }
            break;
        case left:
            snake2.get(0).x--;
            if (snake2.get(0).x < 0) {
                gameOver2=true;
            }
            break;
        case right:
            snake2.get(0).x++;
            if (snake2.get(0).x > width) {
                gameOver2=true;
            }
            break;
    }
    // eat food
    if (foodX2 == snake2.get(0).x && foodY2 == snake2.get(0).y) {
        snake2.add(new Corner( -1,-1));
        newFood2();
    }
//        self destroy When any block of snake touch other
//        Prevent Moving in reverse Direction
    for (int i = 1; i < snake2.size(); i++) {
        if (snake2.get(0).x == snake2.get(i).x && snake2.get(0).y == snake2.get(i).y) {
            gameOver2 = true;
        }
    }

    // fill
    // background
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, width * cornersize, height * cornersize);

    // score
    gc.setFill(Color.WHITE);
    gc.setFont(new Font( 30));
    gc.fillText("Your Score: " + (speed2 - 6), 10, 30);
    // random foodColor
//        Initial color
    Color foodColor2 = Color.WHITE;
    switch (foodcolor2) {
        case 0:
            foodColor2 = Color.DEEPPINK;
            break;
        case 1:
            foodColor2 = Color.CRIMSON;
            break;
        case 2:
            foodColor2 = Color.TOMATO;
            break;
        case 3:
            foodColor2 = Color.GOLD;
            break;
    }
//        Change Color After Each Eat
    gc.setFill(foodColor2);
//        To Fill Color For Food
    gc.fillOval(foodX2 * cornersize, foodY2 * cornersize, cornersize, cornersize);
    // Snake Body
    for (Corner c : snake2) {
//            If We Delete this line the snake body will change according to food
        gc.setFill(Color.GREEN);
        gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 1, cornersize - 1);
    }
}
    // food
    public static void newFood2() {
        while (true) {
            foodX2 = rand.nextInt(width);
            foodY2 = rand.nextInt(height);
            foodcolor2 = rand.nextInt(4);
            speed2++;
            break;

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

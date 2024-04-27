package com.example.hellofx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application {
    private boolean isPaused = false;
    private Timeline scrollTimeline;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 640, 440);

        // Background setup
        Image image = new Image("bg.png");
        ImageView background1 = new ImageView(image);
        ImageView background2 = new ImageView(image);
        background1.setFitWidth(640);
        background2.setFitWidth(640);
        background1.setFitHeight(400);
        background2.setFitHeight(400);

        HBox scrollingBackground = new HBox(background1, background2);
        scrollTimeline = new Timeline(new KeyFrame(Duration.millis(40), e -> scrollBackground(scrollingBackground)));
        scrollTimeline.setCycleCount(Animation.INDEFINITE);
        scrollTimeline.play();

        root.setTop(scrollingBackground);

        // Bottom bar for game stats and controls
        HBox bottomBar = new HBox();
        bottomBar.setPadding(new Insets(5, 10, 5, 10));
        bottomBar.setSpacing(20);
        bottomBar.setAlignment(Pos.CENTER_LEFT);

        Label scoreLabel = new Label("Score: 0");
        Label lifeLabel = new Label("Life: 100%");
        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> toggleGamePause());

        bottomBar.getChildren().addAll(scoreLabel, lifeLabel, pauseButton);
        root.setBottom(bottomBar);

        // Set stage properties
        stage.setTitle("Flappy Enemy");
        stage.setScene(scene);
        stage.setMinWidth(640);
        stage.setMaxWidth(640);
        stage.setMinHeight(465);
        stage.setMaxHeight(465);
        stage.show();
    }

    private void scrollBackground(HBox hbox) {
        if (!isPaused) {
            hbox.setTranslateX(hbox.getTranslateX() - 2);
            if (hbox.getTranslateX() <= -640) {
                hbox.setTranslateX(0);
            }
        }
    }

    private void toggleGamePause() {
        isPaused = !isPaused;
        if (isPaused) {
            scrollTimeline.pause();
        } else {
            scrollTimeline.play();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

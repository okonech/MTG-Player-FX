package TestCode;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FadeInRectangle extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300, Color.BLACK);
        primaryStage.setTitle("JavaFX Scene Graph Demo");       

        Pane pane = new Pane();
        Rectangle rec1 = new Rectangle(0, 0, 300,200);        
        rec1.setFill(Color.RED);
        Rectangle rec2 = new Rectangle(100, 50, 100,100);

        rec2.setStyle("-fx-fill: linear-gradient(to right, left-col, right-col);");

        final DoubleProperty leftEdgeOpacity = new SimpleDoubleProperty(0);
        final DoubleProperty rightEdgeOpacity = new SimpleDoubleProperty(0);

        root.styleProperty().bind(
            Bindings.format("left-col: rgba(0,128,0,%f); right-col: rgba(0,128,0,%f);", leftEdgeOpacity, rightEdgeOpacity)   
        );

        Button btnForward = new Button();
        btnForward.setText(">");
        btnForward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(leftEdgeOpacity, 0)),
                    new KeyFrame(Duration.ZERO, new KeyValue(rightEdgeOpacity, 0)),
                    new KeyFrame(Duration.millis(500), new KeyValue(rightEdgeOpacity, 0)),
                    new KeyFrame(Duration.millis(1500), new KeyValue(leftEdgeOpacity, 1)),
                    new KeyFrame(Duration.millis(2000), new KeyValue(rightEdgeOpacity, 1)),
                    new KeyFrame(Duration.millis(2000), new KeyValue(leftEdgeOpacity, 1))
                );
                timeline.play();
            }
        });        
        pane.getChildren().addAll(rec1,rec2);
        root.getChildren().add(pane);
        root.getChildren().add(btnForward);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
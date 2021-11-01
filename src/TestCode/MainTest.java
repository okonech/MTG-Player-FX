package TestCode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class MainTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        String playerScreen="../application/PlayerScreen/PlayerScreen.fxml";

        Parent root = FXMLLoader.load(getClass().getResource(playerScreen));

        primaryStage.setTitle("Player Screen Testing");

        primaryStage.setScene(new Scene(root));

        primaryStage.show();

    }
    
    public static void main(String[] args) {
        launch(args);
        // TODO Auto-generated method stub
        
    }
}
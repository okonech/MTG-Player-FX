package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) throws Exception {
        // main fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        // new scene with parent
        Scene scene = new Scene(fxmlLoader.load());
        // get controller
        MainScreenController mainController = fxmlLoader.getController();
        
        // default main screen to game tab
        mainController.testLaunchPlayerScreenAction(new ActionEvent());

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("MTG Player FX");
        
        
    }
    

    // for mana https://github.com/micku/mana-cost
}
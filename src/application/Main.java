package application;

import Game.GameState;
import Game.Player;
import application.PlayerScreen.PlayerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // game start
        GameState gameState = new GameState();
        gameState.addPlayer();
        Player currentPlayer = gameState.getPlayers().get(0);

        String playerScreen="PlayerScreen/PlayerScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(playerScreen));
        Parent root = (Parent)fxmlLoader.load(); 
        // pass player ID to controller
        PlayerController playerController = fxmlLoader.<PlayerController>getController();
        playerController.setPlayer(currentPlayer);
        // {card name, set}
        String[][] decklist ={
                {"Horobi, Death's Wail","CHK"},
                {"Isamaru, Hound of Konda","CHK"},
                {"Angelic Captain","BFZ"},
                {"Bane of Bala Ged","BFZ"},
                {"Aligned Hedron Network","BFZ"},
                {"Caustic Wasps","MM"},
                {"Foster","MM"},
                {"Valakut, the Molten Pinnacle","ZEN"},
                {"Iona, Shield of Emeria","V15"},
                {"Necropotence","VOA"},
                {"Necropotence","VMA"},
                {"Necropotence","TKC"},
                {"Necropotence","V09"},
                {"Necropotence","REW"},
                {"Ad Nauseam","ALA"},
                {"Sedris, the Traitor King","ALA"},
                {"Altar of Dementia","CNS"},
                {"a","b"},
                {"a","b"},
                {"a","b"},
                {"a","b"},
                {"a","b"},
                {"a","b"},
                {"a","b"},
                {"a","b"}};
        currentPlayer.loadDeck(decklist);
        playerController.loadPlayerData();
        for(int i=0;i<7;i++){
           currentPlayer.drawCard();
        }


        primaryStage.setTitle("Player Screen Testing");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
        // TODO Auto-generated method stub 
    }


}

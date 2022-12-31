package userInterface;

import Game.Game;
import Images.ImageCache;
import Player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userInterface.PlayerScreen.PlayerController;

public class MainOld extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // game start
        
        Game game = new Game();
        game.addPlayer();
        Player currentPlayer = game.getPlayers().get(0);

        String playerScreen="PlayerScreen/ThreeRowPlayerScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(playerScreen));
        Parent root = (Parent)fxmlLoader.load();
        
        // cache for loaded images
        ImageCache imageCache = new ImageCache();
        // pass player ID to controller
        PlayerController playerController = fxmlLoader.<PlayerController>getController();
        playerController.setPlayer(currentPlayer,imageCache);
        // {card name, set}              
        String[][] decklist ={
                {"Necropotence","VOA"},
                {"Necropotence","TKC"},
                {"Necropotence","V09"},
                {"Necropotence","REW"},
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
                {"Emergent Growth","XLN"},
                };
        currentPlayer.loadDeck(decklist);
        //playerController.loadPlayerData();


        primaryStage.setTitle("Player Screen Testing");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        for(int i=0;i<7;i++){
            playerController.getPlayerActions().drawCard(currentPlayer);
         }
        
    }

    public static void main(String[] args) {
        launch(args);
        // TODO Auto-generated method stub 
    }


}

package userInterface;

import java.io.IOException;

import Game.Game;
import Images.ImageCache;
import Player.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import userInterface.PlayerScreen.PlayerController;

public class MainScreenController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem loadDeck;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private MenuItem testNewTab;

    @FXML
    private TabPane mainTabPane;

    @FXML
    private MenuItem newGame;

    @FXML
    private MenuItem testLaunchPlayerScreen;

    @FXML
    private MenuItem newDeck;

    @FXML
    void newGameAction(ActionEvent event) {

    }

    @FXML
    void exitAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void newDeckAction(ActionEvent event) {

    }

    @FXML
    void loadDeckAction(ActionEvent event) {

    }

    @FXML
    void aboutAction(ActionEvent event) {

    }

    @FXML
    void testNewTabAction(ActionEvent event) {
        final Tab tab = new Tab("Tab " + (mainTabPane.getTabs().size() + 1));
        mainTabPane.getTabs().add(tab);
        mainTabPane.getSelectionModel().select(tab);
    }

    @FXML
    void testLaunchPlayerScreenAction(ActionEvent event) throws IOException {
        String playerScreen="PlayerScreen/FreeFormPlayerScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(playerScreen));
        Node tabContent = fxmlLoader.load();
        
        // cache for loaded images
        ImageCache imageCache = new ImageCache();
        // pass player ID to controller
        PlayerController playerController = fxmlLoader.<PlayerController>getController();
        
        Game game = new Game();
        game.addPlayer();
        Player currentPlayer = game.getPlayers().get(0);
        // pass player ID to controller
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
        for(int i=0;i<7;i++){
            playerController.getPlayerActions().drawCard(currentPlayer);
         }
        
        final Tab tab = new Tab("Tab " + (mainTabPane.getTabs().size() + 1));
        
//        Image dockImage = new Image(DockFX.class.getResource("docknode.png").toExternalForm());
//        // add to dockpane
//        DockPane dockPane = new DockPane();
//        
//        DockNode playerDock = new DockNode(tabContent, "Player 1", new ImageView(dockImage));
//        dockPane.dock(playerDock,DockPos.TOP);
//        
//        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource(playerScreen));
//        Node tabContent2 = fxmlLoader2.load();
//        DockNode playerDock2 = new DockNode(tabContent2, "Player 2", new ImageView(dockImage));
//        dockPane.dock(playerDock2,DockPos.BOTTOM);
//        DockPane.initializeDefaultUserAgentStylesheet();
        
        tab.setContent(tabContent);
        mainTabPane.getTabs().add(tab);
        mainTabPane.getSelectionModel().select(tab);
        
    }

}


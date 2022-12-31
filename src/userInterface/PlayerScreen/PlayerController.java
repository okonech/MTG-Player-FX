package userInterface.PlayerScreen;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import CardBase.Card;
import Game.Zone;
import Images.ImageCache;
import Player.Player;
import Player.PlayerActions;
import Player.PlayerZone;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import userInterface.DragDropHandler;
import userInterface.CardScreen.CardController;
import userInterface.PlayerScreen.CardContainer.CardCellFactory;

// handles events and actions for a single player's area
public class PlayerController {

    @FXML
    private ListView<Card> hand;

    @FXML
    private ScrollPane handContainer;

    @FXML
    private ImageView library;

    // battlefield
    @FXML
    private AnchorPane battlefieldPane;

    private ImageCache imageCache;

    // references to actual data
    private Player player;
    // all possible actions player can take
    private PlayerActions actions;

    void initialize() {
        assert hand != null : "fx:id=\"hand\" was not injected: check your FXML file 'PlayerScreen.fxml'.";
        assert library != null : "fx:id=\"library\" was not injected: check your FXML file 'PlayerScreen.fxml'.";
        assert battlefieldPane != null : "fx:id=\"battlefieldPane\" was not injected: check your FXML file 'PlayerScreen.fxml'.";
    }

    @FXML
    void testClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Test Library Click");
            alert.setHeaderText(player.getName());
            alert.setContentText(player.toString());
            alert.showAndWait();
        } else if (event.getButton() == MouseButton.SECONDARY) {
            actions.drawCard(player);
        }
    }

    public void setPlayer(Player player, ImageCache imageCache) {
        this.player = player;
        player.setController(this);
        System.out.println("PlayerController set: " + player.getName());
        this.imageCache = imageCache;
        this.actions = player.getGame().getPlayerActions();

        // listview things
        hand.setCellFactory(new CardCellFactory(player, player.getHand(),hand));

        // selection
        hand.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // set listeners for model lists
        setModelListListeners();

        // pane drags
        setAreaDragDropListeners();
    }

    private void setModelListListeners(){
        player.getHand().getCardsList().addListener((ListChangeListener<Card>) c -> {
            updateHand(c);
        });
        player.getBattlefield().getCardsList().addListener((ListChangeListener<Card>) c -> {
        	while (c.next()) {
        		if (c.wasAdded()) {
        			addCardsToBattleField(c.getAddedSubList());
        		}
        		if (c.wasRemoved()) {
        			changeRemoveItems(battlefieldPane.getChildren(),c.getRemoved());
        		}
        	}
        });
        player.getGraveyard().getCardsList().addListener((ListChangeListener<Card>) c -> {
            updateGraveyard(c);
        });
        player.getExile().getCardsList().addListener((ListChangeListener<Card>) c -> {
            updateExile(c);
        });
        player.getExileFaceDown().getCardsList().addListener((ListChangeListener<Card>) c -> {
            updateExileFaceDown(c);
        });
    }

    private void setAreaDragDropListeners() {
        battlefieldPane.setOnDragOver(DragDropHandler.onDragOver(this,battlefieldPane,player.getBattlefield()));
        battlefieldPane.setOnDragDropped(DragDropHandler.onDragDropped(this,player.getBattlefield()));
        battlefieldPane.setOnDragDone(DragDropHandler.onDragDone());


    }

    private void updateHand(Change<? extends Card> c) {
        // listview
        hand.setItems(FXCollections.observableArrayList(player.getHand().getCardsList()));

    }

    private void updateLibrary() {
        // do something with this later

    }

    private void updateGraveyard(Change<? extends Card> c) {

    }

    private void addCardsToBattleField(List<? extends Card> addedCards) {
        //battlefieldPane.getChildren().clear();
        for (Card card : addedCards){
            CardController controller = card.getCardController();
            ScrollPane cardContainer = battlefieldCardContainer(controller);
            battlefieldPane.getChildren().add(cardContainer);
            cardContainer.relocate(controller.getXPos()-(cardContainer.getPrefWidth()/2),
            		controller.getYPos()-(cardContainer.getPrefHeight()/2));
        }
    }

    private void updateExile(Change<? extends Card> c) {
        // exile face up and face down
    }

    private void updateExileFaceDown(Change<? extends Card> c) {
        // exile face up and face down
    }
    
    
    private void changeRemoveItems(ObservableList<Node> viewList,List<? extends Card> removedCards) {
    	for (Card card : removedCards){
    		CardController controller = card.getCardController();
    		ScrollPane container = controller.getParentContainer();
    		if(container != null) {
    			viewList.remove(container);
    		}else {
    			viewList.remove(controller.getCardScrollPane());
    		}
    	}
    }
    
    public ScrollPane battlefieldCardContainer (CardController controller) {
    	ScrollPane cardContainer = new ScrollPane(controller.getCardScrollPane());
        cardContainer.prefHeightProperty().bind(Bindings.divide(battlefieldPane.heightProperty(),4.0));
        cardContainer.prefWidthProperty().bind(Bindings.divide(battlefieldPane.heightProperty(),5.73));
        // maybe bind width too
        cardContainer.setFitToHeight(true);
        cardContainer.setFitToWidth(true);
        cardContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        cardContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        controller.setParentContainer(cardContainer);
        return cardContainer;
    }

    public ImageCache getImageCache() {
        return this.imageCache;
    }

    public PlayerActions getPlayerActions(){
        return this.actions;
    }
}

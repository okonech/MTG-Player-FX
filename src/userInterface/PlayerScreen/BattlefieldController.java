package userInterface.PlayerScreen;

import java.util.Random;

import CardBase.Card;
import Player.Player;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import userInterface.CardScreen.CardController;

public class BattlefieldController {
    // handles all battlefield cards for the playercontroller
    // automatically distributes cards across 3 zones
   
    // battlefield
    @FXML
    private AnchorPane battlefieldPane;
    
    @FXML
    private TilePane creaturesPane;
    
    @FXML
    private TilePane nonLandPane;
    
    @FXML
    private TilePane manaProducingPane;
    
    private Player player;
    
    private ObservableList<Card> battlefield;
    
    
    
    public BattlefieldController(Player player,AnchorPane battlefieldPane, TilePane creaturesPane, TilePane nonLandPane, TilePane manaProducingPane) {
        this.player = player;
        this.battlefield = player.getBattlefield().getCardsList();
        this.battlefieldPane=battlefieldPane;
        this.creaturesPane=creaturesPane;
        this.nonLandPane=nonLandPane;
        this.manaProducingPane=manaProducingPane;
        
        //creaturesPane.prefTileHeightProperty().bind(creaturesPane.heightProperty());
        //creaturesPane.prefTileHeightProperty().bind(getCreaturesPaneTileHeightProperty());
        
        DoubleBinding calcHeight = Bindings.divide(battlefieldPane.prefHeightProperty(),3.0);
        creaturesPane.prefWidthProperty().bind(calcHeight);
        nonLandPane.prefWidthProperty().bind(calcHeight);
        manaProducingPane.prefWidthProperty().bind(calcHeight);
        
    }
    
    public void setDragDropHandlers(){
//        creaturesPane.setOnDragDetected(DragDropHandler.onDragDetected(player, creaturesPane, null,"Battlefield"));
//        creaturesPane.setOnDragOver(DragDropHandler.onDragOver(player, creaturesPane, null));
//        creaturesPane.setOnDragEntered(DragDropHandler.onDragEntered(creaturesPane));
//        creaturesPane.setOnDragEntered(DragDropHandler.onDragExited(creaturesPane));
//        creaturesPane.setOnDragDropped(DragDropHandler.onDragDropped(player, creaturesPane, null,"Battlefield"));
//        creaturesPane.setOnDragDone(DragDropHandler.onDragDone(creaturesPane));
//        
//        nonLandPane.setOnDragDetected(DragDropHandler.onDragDetected(player, nonLandPane, null,"Battlefield"));
//        nonLandPane.setOnDragOver(DragDropHandler.onDragOver(player, nonLandPane, null));
//        nonLandPane.setOnDragEntered(DragDropHandler.onDragEntered(nonLandPane));
//        nonLandPane.setOnDragEntered(DragDropHandler.onDragExited(nonLandPane));
//        nonLandPane.setOnDragDropped(DragDropHandler.onDragDropped(player, nonLandPane, null,"Battlefield"));
//        nonLandPane.setOnDragDone(DragDropHandler.onDragDone(nonLandPane));
//        
//        manaProducingPane.setOnDragDetected(DragDropHandler.onDragDetected(player, manaProducingPane, null,"Battlefield"));
//        manaProducingPane.setOnDragOver(DragDropHandler.onDragOver(player, manaProducingPane, null));
//        manaProducingPane.setOnDragEntered(DragDropHandler.onDragEntered(manaProducingPane));
//        manaProducingPane.setOnDragEntered(DragDropHandler.onDragExited(manaProducingPane));
//        manaProducingPane.setOnDragDropped(DragDropHandler.onDragDropped(player, manaProducingPane, null,"Battlefield"));
//        manaProducingPane.setOnDragDone(DragDropHandler.onDragDone(manaProducingPane));
        
        
    }
    
    public void updateBattlefield() {
        
        creaturesPane.getChildren().clear();
        nonLandPane.getChildren().clear();
        manaProducingPane.getChildren().clear();
        for (Card card :battlefield){
            //card into battlefield cell
            
            Random ran = new Random();
            int x = ran.nextInt(3)+1;
            ScrollPane cardDisplay;
            switch (x) {
            case 1:
                cardDisplay = getBattlefieldCell(card,creaturesPane);
                creaturesPane.getChildren().add(cardDisplay);
                break;
            case 2:
                cardDisplay = getBattlefieldCell(card,nonLandPane);
                nonLandPane.getChildren().add(cardDisplay);
                break;
            case 3:
                cardDisplay = getBattlefieldCell(card,manaProducingPane);
                manaProducingPane.getChildren().add(cardDisplay);
                break;
            }

        }

    }
    
    public ScrollPane getBattlefieldCell(Card card, TilePane parent){
        ScrollPane cardUI = card.getCardController().getCardUI();
        ScrollPane displayPane = new ScrollPane();
        //bCell.setBackground(value);
        displayPane.setContent(cardUI);
        // disable scrollpane scrolling
        displayPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        displayPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // set grow settings and ratio
        DoubleBinding calcHeight = Bindings.subtract(parent.heightProperty(),5.0);
        displayPane.prefHeightProperty().bind(calcHeight);
        double cardWidthHeightRatio = (CardController.DEF_WIDTH / CardController.DEF_HEIGHT);
        DoubleBinding calcWidth = Bindings.multiply(displayPane.prefHeightProperty(), cardWidthHeightRatio);
        displayPane.prefWidthProperty().bind(calcWidth);
        
        displayPane.setFitToHeight(true);
        displayPane.setFitToWidth(true);
        
        return displayPane;
    }
    
    private ObservableValue<? extends Number> getCreaturesPaneTileHeightProperty() {
        // TODO Auto-generated method stub
        return null;
    }
    
    private ObservableValue<? extends Number> getNonLandPaneTileHeightProperty() {
        // TODO Auto-generated method stub
        return null;
    }
    
    private ObservableValue<? extends Number> getManaProducingPaneTileHeightProperty() {
        // TODO Auto-generated method stub
        return null;
    }
    
    private ObservableValue<? extends Number> desiredTileHeightProperty(TilePane main,TilePane a,TilePane b) {
        // return height property for main pane based on contents of all 3 panes and height of bf
        ReadOnlyDoubleProperty bfHeightProp = battlefieldPane.heightProperty();
        //main.getChildrenUnmodifiable().
        return null;
    }
    

}

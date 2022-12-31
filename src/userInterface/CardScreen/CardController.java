package userInterface.CardScreen;

import java.io.IOException;
import CardBase.Card;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class CardController {
    
    public static final double DEF_WIDTH = 300;
    public static final double DEF_HEIGHT = 413;

    @FXML
    ImageView imageView;

    @FXML
    ScrollPane scrollPane;

    private Card card;
    private double xPos;
    private double yPos;
    private ScrollPane parentContainer;

    void initialize() {
        assert imageView != null : "fx:id=\"hand\" was not injected: check your FXML file 'CardScreen.fxml'.";
        assert scrollPane != null : "fx:id=\"library\" was not injected: check your FXML file 'CardScreen.fxml'.";
    }
    
    public static CardController makeController(Card card) {
        FXMLLoader fxmlLoader = new FXMLLoader(
                card.getClass().getResource("../../userInterface/CardScreen/CardScreen.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CardController cardController = fxmlLoader.<CardController> getController();
        cardController.setCard(card);
        cardController.getCardScrollPane().getProperties().put("Controller",cardController);
        cardController.setPosition(0.0,0.0);
        
        return cardController;
    }

    public void setCard(Card card) {
        this.card = card;
        // load card image
        imageView.setImage(card.getPlayerOwner().getController().getImageCache().getCachedCardImage(card));
        
        imageView.fitHeightProperty().bind(scrollPane.heightProperty());
        imageView.fitWidthProperty().bind(scrollPane.widthProperty());
        //imageView.setPreserveRatio(true);
    }
    
    public Card getCard() {
        return card;
    }
    
    public ScrollPane getCardUI() {
        return scrollPane;
    }
    
    public ImageView getCardImageView(){
        return imageView;
    }
    
    public ScrollPane getCardScrollPane(){
        return scrollPane;
    }
    
    public void setPosition(Double xPos, Double yPos){
        this.xPos=xPos;
        this.yPos=yPos;
    }
    
    public Double getXPos(){
        return xPos;
    }
    
    public Double getYPos(){
        return yPos;
    }
    
    public ScrollPane getParentContainer() {
    	return parentContainer;
    }
    
    public void setParentContainer(ScrollPane cardContainer) {
    	this.parentContainer=cardContainer;
    }


    @FXML
    // Defines a function to be called when a mouse button has been clicked (pressed and released) on this Node.
    public void onCardClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Test Card Click");
            alert.setHeaderText(card.getName());
            alert.setContentText(card.toString() + " width: " + scrollPane.getWidth() + " height: " + scrollPane.getHeight());
            alert.showAndWait();
        } else if (event.getButton() == MouseButton.SECONDARY) {
            showFullSizeCard();
        } else if (event.getButton() == MouseButton.MIDDLE) {
            card.getPlayerOwner().getGame().getPlayerActions().discardCard(card.getPlayerOwner(), card);
        }
    }
    
    @FXML
    //Defines a function to be called when drag gesture has been detected. This is the right place to start drag and drop operation.
    public void onCardDragDetected(MouseEvent event) {
            Dragboard dragboard = scrollPane.startDragAndDrop(TransferMode.MOVE);
            // get snapshot of card to use for drag
            final Image snapshot = scrollPane.snapshot(new SnapshotParameters(), null);
            System.out.println(snapshot.getWidth() + " " + snapshot.getHeight());
            if(parentContainer != null)
            	System.out.println(parentContainer.getWidth()+ " " + parentContainer.getHeight());
            
            // make dragboard recognized as having content
            ClipboardContent content = new ClipboardContent();
            content.putString(card.getName() + card.getZone());
            dragboard.setContent(content);
           
            //content.put(cardFormat, currentCard);
            dragboard.setDragView(snapshot, snapshot.getWidth()/2, snapshot.getHeight()/2);
            event.consume();
    }
    
    @FXML
    // Defines a function to be called when this Node is a drag and drop gesture source after its data has
    // been dropped on a drop target. The transferMode of the event shows what just happened at the drop target. 
    // If transferMode has the value MOVE, then the source can clear out its data. Clearing the source's data gives 
    // the appropriate appearance to a user that the data has been moved by the drag and drop gesture. 
    // A transferMode that has the value NONE indicates that no data was transferred during the drag and drop gesture.
    public void onCardDragDone(DragEvent event) {
        event.consume();
    }
    
    @FXML
    // Defines a function to be called when the mouse button is released on this Node during drag and drop gesture. 
    // Transfer of data from the DragEvent's dragboard should happen in this function.
    public void onCardDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean succeeded = false;
        if (db.hasString()) {
            
            // check if zone of card of this controller = hand, rearrange
            // if zone = battlefield try to equip/imprint
            // if zone = stack put on top of stack targeting dragged on card
            
            Card draggedCard = ((CardController)((Node) event.getGestureSource()).getProperties().get("Controller")).getCard();
            ObservableList<Card> draggedFromList = draggedCard.getZone().getCardsList();
            
            Card dropTargetCard = card;
            ObservableList<Card> draggedToList = dropTargetCard.getZone().getCardsList();
            
            // remove card from dragged to list as start of list rearrange
            draggedToList.remove(draggedCard);
            // remove card from original list, has to happen before adds 
            // in case it's added to the same list
            draggedFromList.remove(draggedCard);
            // get index of target card in it's list
            int idx = draggedToList.indexOf(dropTargetCard);
            //move card to new list, relative to target card if position found in list
            if (idx>=0){
                // modify index based on position in clicked region
                if((event.getX() > scrollPane.getWidth()/2)){
                    // move to right of card if move than halfway on card
                    idx++;
                }
                    
                draggedToList.add(idx, draggedCard);
            } else {
                draggedToList.add(draggedCard);   
            }
            draggedCard.updateZone(dropTargetCard.getZone());
            
            // mark drop operation success
            succeeded= true;
        }
        event.setDropCompleted(succeeded);
        event.consume();
        
        
    }
    
    @FXML
    // Defines a function to be called when drag gesture enters this Node.
    public void onCardDragEntered(DragEvent event) {
        if(event.getDragboard().hasString()){
            scrollPane.setOpacity(0.5);
        }
    }
    
    @FXML
    // Defines a function to be called when drag gesture exits this Node.
    public void onCardDragExited(DragEvent event) {
        if(event.getDragboard().hasString()){
            scrollPane.setOpacity(1);
        }
    }
    
    @FXML
    // Defines a function to be called when drag gesture progresses within this Node.
    public void onCardDragOver(DragEvent event) {
        if(event.getDragboard().hasString()){
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void showFullSizeCard() {
        Stage popupStage = new Stage();
        // stage not resizable
        popupStage.setResizable(false);
        ScrollPane scrollPane = new ScrollPane();
        // disable scrollpane scrolling
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        ImageView img = new ImageView(card.getPlayerOwner().getController()
                .getImageCache().getCachedCardImage(card));
        img.fitWidthProperty().bind(scrollPane.widthProperty());
        img.fitHeightProperty().bind(scrollPane.heightProperty());
        // put image in scrollpane
        scrollPane.setContent(img);
        Scene scene = new Scene(scrollPane,DEF_WIDTH,DEF_HEIGHT);
        popupStage.setScene(scene);
        popupStage.show();
        popupStage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean t, Boolean t1) {
                popupStage.close();
            }
        });
    }

}
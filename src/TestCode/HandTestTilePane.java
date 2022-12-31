package TestCode;

import DragDrop.DragDropHandler;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.SelectableNode;
import jfxtras.labs.scene.control.window.Window;
import jfxtras.labs.scene.layout.ScalableContentPane;
import jfxtras.labs.util.event.MouseControlUtil;

public class HandTestTilePane extends Application {

	@Override
    public void start(Stage stage) throws Exception {
        SplitPane splitPane=new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        ScalableContentPane top = new ScalableContentPane();
        top.setStyle("-fx-background-color: green");
        top.setMinHeight(40.0);
        top.setPrefHeight(600);
        AnchorPane topContent = new AnchorPane();
        top.setContentPane(topContent);
        topContent.setStyle("-fx-background-color: red");
        
        //Shape shape = new Rectangle(1,1);
        //topContent.getChildren().add(shape);
        //shape.setLayoutX(0);
        //shape.setLayoutY(1800);
        
        //pref height prevents scaling on down drag
        topContent.setPrefHeight(1800);
        
        MouseControlUtil.addSelectionRectangleGesture(topContent, new Rectangle(1,1));
        
        //10 cards to top pane
        for(int i=0;i<10;i++) {
        	ImageView imageView = new ImageView(new Image("/Images/MTGCardBack.jpg"));
        	SelectableStackPane cardPane = new SelectableStackPane();
        	cardPane.getChildren().add(imageView);
        	MouseControlUtil.makeDraggable(cardPane);
        	cardPane.setOnMouseReleased(DragDropHandler.onMouseReleasedDraggable(topContent,top));
        	topContent.getChildren().add(cardPane);
        }
        
        ScrollPane handContainer = new ScrollPane();
        TilePane hand = new TilePane();
        handContainer.setMinHeight(100);
        hand.prefTileHeightProperty().bind(Bindings.subtract(handContainer.heightProperty(),15.0));
        addCards(hand);
        
        hand.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override 
            public void changed(ObservableValue<? extends Bounds> observableValue, Bounds oldBounds, Bounds newBounds) {
            	hand.setMinWidth(hand.getChildren().size()*newBounds.getHeight()/1.376666666666667);
            }
          });
       
        
        handContainer.setFitToWidth(true);
        handContainer.setFitToHeight(true);
        handContainer.setContent(hand);
        
        
        handContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        handContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        splitPane.getItems().add(top);
        splitPane.getItems().add(handContainer);
        Scene scene=new Scene(splitPane, 600, 800);
        stage.setScene(scene);
        stage.show();
    }
	
	private void addCards(TilePane hand) {
		for(int i=0;i<7;i++) {
			hand.getChildren().add(makeCard(hand));
		}
	}
	
	private ScrollPane makeCard(TilePane hand) {
		ScrollPane card = new ScrollPane();
		ImageView imageView = new ImageView(new Image("/Images/MTGCardBack.jpg"));
		StackPane stackPane = new StackPane(imageView);
		
		imageView.fitHeightProperty().bind(card.heightProperty());
		imageView.fitWidthProperty().bind(card.widthProperty());
		
		card.setContent(imageView);
		card.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		card.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		card.prefHeightProperty().bind(hand.prefTileHeightProperty());
		card.prefWidthProperty().bind(Bindings.divide(card.heightProperty(),1.376666666666667));
		
		return card;
	}

    public static void main(String[] args) {
        launch(args);
    }
    
    public class SelectableStackPane extends StackPane implements SelectableNode  {

		public SelectableStackPane(Node... children) {
			// TODO Auto-generated constructor stub
			super();
		}

		@Override
		public boolean requestSelection(boolean select) {
			// TODO Auto-generated method stub
			return true;
		}
    	
    }
}

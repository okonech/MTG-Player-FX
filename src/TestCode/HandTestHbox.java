package TestCode;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HandTestHbox extends Application {

	@Override
    public void start(Stage stage) throws Exception {
        SplitPane splitPane=new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        HBox top=new HBox();
        top.setStyle("-fx-background-color: green");
        top.setMinHeight(40.0);
        top.setPrefHeight(600);
        ScrollPane handContainer = new ScrollPane();
        
        HBox hand = new HBox();
        hand.setFillHeight(true);
        
        addCards(hand);
       
        
        handContainer.setFitToWidth(true);
        handContainer.setFitToHeight(true);
        handContainer.setContent(hand);
        
        
        //handContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        handContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        splitPane.getItems().add(top);
        splitPane.getItems().add(handContainer);
        Scene scene=new Scene(splitPane, 600, 800);
        stage.setScene(scene);
        stage.show();
    }
	
	private void addCards(HBox hand) {
		for(int i=0;i<7;i++) {
			ScrollPane card = makeCard(hand);
			hand.getChildren().add(card);
			//HBox.setHgrow(card, Priority.ALWAYS);
		}
	}
	
	private ScrollPane makeCard(HBox hand) {
		ScrollPane card = new ScrollPane();
		ImageView imageView = new ImageView(new Image("/Images/MTGCardBack.jpg"));
		
		imageView.fitHeightProperty().bind(hand.prefHeightProperty());
		imageView.fitWidthProperty().bind(card.widthProperty());
		imageView.setPreserveRatio(true);
		
		card.setContent(imageView);
		card.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		card.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		card.prefHeightProperty().bind(hand.prefHeightProperty());
		//card.prefWidthProperty().bind(Bindings.divide(card.prefHeightProperty(),1.376666666666667));
		card.minWidthProperty().bind(Bindings.divide(card.heightProperty(),1.376666666666667));
		
		return card;
	}

    public static void main(String[] args) {
        launch(args);
    }
}

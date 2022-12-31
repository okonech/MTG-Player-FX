package DragDrop;

import CardBase.Card;
import Player.PlayerZone;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import userInterface.CardScreen.CardController;
import userInterface.PlayerScreen.PlayerController;

// handle all drag/drops among all zones and areas

public class DragDropHandler {

	// drag into some list of cards, call corresponding player action on dragged card(s)
	public static EventHandler<? super DragEvent> onDragDropped(PlayerController playerController, PlayerZone zone) {
		return new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {

				Dragboard db = event.getDragboard();
				boolean succeeded = false;
				if (db.hasString()) {

					CardController draggedCardController = (CardController) ((Node) event.getGestureSource()).getProperties().get("Controller");
					Card draggedCard = draggedCardController.getCard();
					ScrollPane draggedCardContainer = draggedCardController.getParentContainer();
					ObservableList<Card> draggedFromList = draggedCard.getZone().getCardsList();
					ObservableList<Card> draggedToList = zone.getCardsList();

					// put released coordinates to controller for positioning
					// must be done before dragged to observable list update triggers
					draggedCardController.setPosition(event.getX(),event.getY());
					// add to drag end if not already there (don't rearrange)
					if(!draggedToList.contains(draggedCard)){
						// remove from drag start
						draggedFromList.remove(draggedCard);
						draggedToList.add(draggedCard);
						draggedCard.updateZone(zone);
					} else{
						// just relocate on move within same pane
						draggedCardContainer.relocate(draggedCardController.getXPos()-(draggedCardController.getCardStackPane().getWidth()/2),
								draggedCardController.getYPos()-(draggedCardController.getCardStackPane().getHeight()/2));
					}


					// mark drop operation success
					succeeded= true;
				}
				event.setDropCompleted(succeeded);
				event.consume();
			}
		};
	}

	public static EventHandler<? super DragEvent> onDragOver(PlayerController playerController, Pane cardContainer, PlayerZone zone) {
		return new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if(event.getDragboard().hasString()){
					// only accept drag drop if there's an identifying string
					event.acceptTransferModes(TransferMode.MOVE);
					//                    CardController draggedCardController = (CardController) ((Node) event.getGestureSource()).getProperties().get("Controller");
					//                    ScrollPane snapshotPane = playerController.battlefieldCardContainer(draggedCardController);
					//                    final Image snapshot = snapshotPane.snapshot(new SnapshotParameters(), null);
					//                    event.getDragboard().setDragView(snapshot, snapshot.getWidth()/2, snapshot.getHeight()/2);
				}
				event.consume();
			}
		};
	}

	public static EventHandler<? super DragEvent> onDragDone() {
		return new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				event.consume();
			}
		};
	}

	public static EventHandler<MouseEvent> onMouseReleasedDraggable(Pane parent,Pane container) {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Node source = ((Node)event.getSource());
				// coords in parent
				Double x = source.getLayoutX()+ source.getLayoutBounds().getMinX();
				Double y = source.getLayoutY()+ source.getLayoutBounds().getMinY();
				
				// don't allow negative x coord
				if(x<0) {
					source.setLayoutX(0 - source.getLayoutBounds().getMinX());
				}
				
				Double maxY = source.getBoundsInParent().getMaxY();
				System.out.println(y + " " + maxY);
				System.out.println(parent.getHeight());
				System.out.println(parent.getBoundsInParent().getMaxY());
				
				// don't allow out of bounds y coord in parent
				if(maxY>parent.getHeight()) {
					source.setLayoutY(parent.getHeight()-(maxY-y) - source.getLayoutBounds().getMinY());
				}
			}
		};
	}

}

package application.PlayerScreen;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import Game.Player;

public class PlayerController {

    @FXML
    private HBox hand;

    @FXML
    private ImageView library;

    @FXML
    private Label colorlessMana;

    @FXML
    private Label whiteMana;

    @FXML
    private Label blueMana;

    @FXML
    private Label blackMana;

    @FXML
    private Label redMana;

    @FXML
    private Label greenMana;

    // references to actual data
    private Player player;

    void initialize() {
        assert hand != null : "fx:id=\"hand\" was not injected: check your FXML file 'PlayerScreen.fxml'.";
        assert library != null : "fx:id=\"library\" was not injected: check your FXML file 'PlayerScreen.fxml'.";
    }

    @FXML
    void testClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Test Player Click");
            alert.setHeaderText(player.getName());
            alert.setContentText(player.toString());
            alert.showAndWait();
        } else if (event.getButton() == MouseButton.SECONDARY) {
            player.drawCard();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setController(this);
        System.out.println("PlayerController set: " + player.getName());
    }

    public void loadPlayerData() {
        updateHand();
    }

    public void updateHand() {
        // ObservableList<AnchorPane> handUI = player.getHandUI();
        // this.hand.setItems(handUI);
        // this.hand.setItems(player.getHandUI());
        hand.getChildren().setAll(player.getHandUI());
    }

    public void setListeners() {
        // TODO Auto-generated method stub

    }

}

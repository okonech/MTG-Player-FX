package TestCode;

import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener.Change;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class SelectableGridPane extends Application {

    private final ObservableSet<Label> selectedLabels =
            FXCollections.observableSet();


    private final int ROWS = 10 ;
    private final int COLS = 10 ;

    private final Label[][] labels = new Label[COLS][ROWS];

    private final PseudoClass SELECTED =
            PseudoClass.getPseudoClass("selected");

    @Override
    public void start(Stage primaryStage) {
        GridLocation mouseDownLoc = new GridLocation();
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setGridLinesVisible(true);

        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                addLabel(grid, i, j, mouseDownLoc);
            }
        }

        selectedLabels.addListener((Change<? extends Label> change) -> {
            if (change.wasAdded()) {
                Label label = change.getElementAdded();
                label.pseudoClassStateChanged(SELECTED, true);
            } else if (change.wasRemoved()) {
                Label label = change.getElementRemoved();
                label.pseudoClassStateChanged(SELECTED, false);
            }
        });

        Scene scene = new Scene(grid, 800, 800);
        scene.getStylesheets().add("TestCode/dragging-grid-pane.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addLabel(GridPane grid, int col, int row,
            GridLocation mouseDownLoc) {
        Label label = new Label("Cell ["+(col+1)+", "+(row+1)+"]");
        labels[col][row] = label ;

        grid.add(label, col, row);
        GridPane.setFillWidth(label, true);
        GridPane.setHgrow(label, Priority.ALWAYS);
        GridPane.setVgrow(label, Priority.ALWAYS);

        label.setAlignment(Pos.CENTER);
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        label.setOnDragDetected(event -> {
            mouseDownLoc.x = col ;
            mouseDownLoc.y = row ;
            selectedLabels.clear();
            selectedLabels.add(label);
            label.startFullDrag();
        });

        label.setOnMouseDragEntered(event -> 
                recomputeSelection(mouseDownLoc, col, row));

    }

    private void recomputeSelection(GridLocation mouseDown, 
            int x, int y) {
        Set<Label> newSelection = new HashSet<>();
        int startX = Math.min(x,  mouseDown.x);
        int endX = Math.max(x, mouseDown.x);
        int startY = Math.min(y, mouseDown.y);
        int endY = Math.max(y, mouseDown.y);

        for (int j = startY; j <= endY; j++) {
            for (int i = startX; i <= endX; i++) {
                newSelection.add(labels[i][j]);
            }
        }

        // remove anything in selectedLabels 
        // that is not in newSelection:
        selectedLabels.retainAll(newSelection);

        // add everything from newSelection
        // to selectedLabels (will not duplicate):
        selectedLabels.addAll(newSelection);
    }

    private static class GridLocation {
        int x, y ;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

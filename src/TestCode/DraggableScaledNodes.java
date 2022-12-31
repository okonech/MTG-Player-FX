package TestCode;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;
import jfxtras.labs.scene.layout.ScalableContentPane;
import jfxtras.labs.util.event.MouseControlUtil;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class DraggableScaledNodes extends Application {

    @Override
    public void start(Stage primaryStage) {

        // all content can be scaled
        ScalableContentPane scaledPane = new ScalableContentPane();

        // use the content as root pane
        Pane root = scaledPane.getContentPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom, rgb(20,20,20), rgb(30,60,80));");

        // add a window control that contains scalable content
        ScalableContentPane windowContent = new ScalableContentPane();
        Window w = new Window("Window");
        windowContent.setMinSize(200, 200);
        w.setContentPane(windowContent);
        
        root.getChildren().add(w);
        
        
        // offset and gaps for the shapes
        int offset = 5;
        int gap = 10;
        int size = 50;

        // add rectangles and make them draggable
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {

                Shape shape = new Rectangle(size, size);
                shape.setFill(new Color(0,0,0,0.5));
                shape.setStroke(Color.WHITE);
                shape.setStrokeWidth(3);
                shape.setLayoutX(offset+x*(size + gap));
                shape.setLayoutY(offset+y*(size + gap));
                MouseControlUtil.makeDraggable(shape);
                windowContent.getContentPane().getChildren().add(shape);
                
            }
        }


        // add the scalable pane to the scene
        Scene scene = new Scene(scaledPane, 400, 400);

        // setup the stage
        primaryStage.setTitle("MakeNodesDraggable01");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}


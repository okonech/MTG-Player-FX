package TestCode;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPaneBuilder;
import javafx.scene.control.SplitPane;
import javafx.scene.control.SplitPaneBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPaneBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class ScaleTest extends Application
{
     //~ Methods ----------------------------------------------------------------

     @Override
     public void start(final Stage stage) throws Exception
     {
          final ImageView imageView =
              ImageViewBuilder.create()
                              .image(new Image("http://www.google.com/intl/en_ALL/images/logos/images_logo_lg.gif"))
                              .build();
          final Shape overlayRectangle = new Rectangle(100, 100, 200, 200);
          final AnchorPane anchorPane = AnchorPaneBuilder.create().children(imageView, overlayRectangle).build();
          overlayRectangle.toFront();

          final Group wrapperGroup = GroupBuilder.create().children(anchorPane).build();
          final VBox box = VBoxBuilder.create().children(wrapperGroup).build();
          final ScrollPane scroll = ScrollPaneBuilder.create().content(box).build();
          final SplitPane split = SplitPaneBuilder.create().items(scroll, new Pane()).build();
          final Button button =
              ButtonBuilder.create().text("1/2 scale").onAction(new EventHandler<ActionEvent>() {
                         @Override
                         public void handle(final ActionEvent event)
                         {
                              anchorPane.getTransforms().add(new Scale(0.5, 0.5));
                         }
                    }).build();

          final BorderPane border = BorderPaneBuilder.create().center(split).bottom(button).build();
          final Scene scene = SceneBuilder.create().root(border).build();
          
          anchorPane.getTransforms().add(new Scale(0.5, 0.5));
          anchorPane.requestLayout();
          
          stage.setScene(scene);
          stage.show();
     }

     public static void main(final String[] args)
     {
          launch();
     }
}
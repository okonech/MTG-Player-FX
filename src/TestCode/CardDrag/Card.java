package TestCode.CardDrag;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Card extends Rectangle {

    static Random rand = new Random();

    public Card() {


        setWidth(100);
        setHeight(200);

        Color color = createRandomColor();

        setStroke(color);
        setFill( color.deriveColor(1, 1, 1, 0.4));

    }

    public static Color createRandomColor() {

        int max = 200;

        Color color = Color.rgb( (int) (rand.nextDouble() * max), (int) (rand.nextDouble() * max), (int) (rand.nextDouble() * max));

        return color;
    }
}

package Images;

import java.util.HashMap;

import CardBase.Card;
import javafx.scene.image.Image;

public class ImageCache {
    // key = multiverse ID
    private HashMap<String, Image> cardImages;
    private String folderPath;
    private ImageFileHandler fileHandler;

    public ImageCache() {
        this.cardImages = new HashMap<String, Image>();
        this.folderPath = "F:/Games/PlayerFx/Pics/";
        this.fileHandler= new ImageFileHandler(folderPath);
    }

    public Image getCachedCardImage(Card card) {
        // get image by uuid from card
        Image cardImage = cardImages.get(card.getCardUUID());
        if(cardImage==null){
            cardImage=fileHandler.getImage(card);
            // add newly loaded image to cache
            cardImages.put(card.getCardUUID(), cardImage);
        } else {
            System.out.println("Found in cache");
        }
        return cardImage;
    }

}

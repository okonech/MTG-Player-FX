package Images;

import java.io.File;
import java.util.HashMap;

import CardBase.Card;
import javafx.scene.image.Image;

public class ImageCache {
    // key = multiverse ID
    private HashMap<String, Image> cardImages;
    private String folderPath;

    public ImageCache() {
        this.cardImages = new HashMap<String, Image>();
        this.folderPath = "F:/Program Files (x86)/Magic Workstation/Pics/";
    }

    public Image getCachedCardImage(Card card) {
        // get image by multiverseID from card
        Image cardImage = cardImages.get(card);
        // load local
        if (cardImage == null) {
            cardImage = loadLocalImage(card);
        }
        // default to load from internet
        if (cardImage == null) {
            cardImage = loadGathererCardImage(card);
        }
        return cardImage;
    }

    public Image loadGathererCardImage(Card card) {
        System.out
                .println("Load image from gatherer: " + card.getMultiverseID());
        Image cardImage = new Image(
                "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid="
                        + card.getMultiverseID() + "&type=card");
        // add newly loaded image to cache
        cardImages.put(card.getMultiverseID(), cardImage);
        return cardImage;
    }

    public Image loadLocalImage(Card card) {
        String path = folderPath + card.getSet() + "/" + card.getName();
        // try to load full image (.full)
        File imageFile = new File(path + ".full.jpg");
        if (imageFile.isFile()) {
            Image cardImage = new Image(imageFile.toURI().toString());
            // add newly loaded image to cache
            cardImages.put(card.getMultiverseID(), cardImage);
            return cardImage;
        } else {
            return null;
        }

    }

}

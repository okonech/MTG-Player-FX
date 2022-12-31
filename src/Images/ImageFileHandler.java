package Images;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import CardBase.Card;
import javafx.scene.image.Image;

public class ImageFileHandler {

    private String folderPath;

    public ImageFileHandler(String folderPath) {
        this.folderPath=folderPath;
    }

    public Image getImage(Card card) {
        // load local
        Image cardImage = loadLocalImage(card);
        if(cardImage==null){
            cardImage = loadDownloadedImage(card);
        }
        return cardImage;
    }

    private Image loadDownloadedImage(Card card){

        if(loadScryfallCardImage(card) || loadGathererCardImage(card)){
            return loadLocalImage(card);
        } else {
            return null;
        }
    }

    private boolean downloadImage(URL url, Card card){
        try {
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            // fake a user agent so we get images as if we are a browser
            httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            httpConn.connect();
            int responseCode = httpConn.getResponseCode();

            switch (responseCode) {
            case 200:
                File newCardFile = new File(folderPath+"/"+card.getSetCode()+"/"+card.getName()+".full.jpg");
                // make directories as needed for files
                newCardFile.getParentFile().mkdirs();
                OutputStream fileOutStream = new FileOutputStream(folderPath+"/"+card.getSetCode()+"/"+card.getName()+".full.jpg");
                BufferedInputStream connInputStream = new BufferedInputStream(httpConn.getInputStream());
                byte[] b = new byte[2048];
                int length;
                while ((length = connInputStream.read(b)) != -1) {
                    fileOutStream.write(b, 0, length);
                }
                connInputStream.close();
                fileOutStream.close();
                return true;
            default:
                return false;

            }
        } catch (IOException e) {
            return false;
        }
    }

    // needs ssl rework because of the https requirement
    // currently redirects to https and fails
    private boolean loadMagicCardsInfoImage(Card card) {
        System.out.println("Load image from magiccards.info: " + card.getName() + " " + card.getSetCode());
        String set = card.getSetMagicCardsInfo();
        String number = card.getMciNumber();
        if((set == null) || (number == null)){
            return false;
        } else {
            try {
                URL url = new URL("http://magiccards.info/scans/en/" + set + "/" + number + ".jpg");
                return downloadImage(url,card);
            } catch (MalformedURLException e) {
                return false;
            }

        }
    }


    private boolean loadScryfallCardImage(Card card) {
        System.out.println("Load image from scryfall: " + card.getName() + " " + card.getSetCode());
        String set = card.getSetCode();
        String number = card.getSetNumber();
        if((set == null) || (number == null)){
            return false;
        } else {
            try {
                URL url = new URL("https://img.scryfall.com/cards/normal/en/" + card.getSetCode().toLowerCase() + 
                        "/" + card.getSetNumber() + ".jpg?");
                return downloadImage(url,card);
            } catch (MalformedURLException e) {
                return false;
            }

        }
    }

    private boolean loadGathererCardImage(Card card) {
        System.out.println("Load image from gatherer: " + card.getName() + " " + card.getSetCode());
        String id = card.getMultiverseID();
        if(id == null){
            return false;
        } else {
            try {
                URL url = new URL("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid="
                        + id + "&type=card");
                return downloadImage(url,card);
            } catch (MalformedURLException e) {
                return false;
            }
        }
    }





    public Image loadLocalImage(Card card) {
        System.out.println("Load image from disk: " + card.getName() + " " + card.getSetCode());
        String path = folderPath + card.getSetCode() + "/" + card.getName();
        // try to load full image (.full)
        File imageFile = new File(path + ".full.jpg");
        if (imageFile.isFile()) {
            Image cardImage = new Image(imageFile.toURI().toString());
            return cardImage;
        } else {
            return null;
        }

    }

}


//https://stackoverflow.com/questions/10292792/getting-image-from-url-java

//https://github.com/magefree/mage/blob/39ca5a13f43e4fe41250156607a6fbc56d0a4642/Mage.Client/src/main/java/org/mage/plugins/card/images/DownloadPictures.java
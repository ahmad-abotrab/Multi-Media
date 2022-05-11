import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        ProcessOnImage processOnImage = new ProcessOnImage();
        //read image
        BufferedImage image = processOnImage.readImage("images/Unknown.png","/Users/ahmadabotrab/Desktop/multimediaProject/src/");
        // process to convert image from RGB to gray
        processOnImage.rgb2gray(image);
        processOnImage.writeImage(image,"/Users/ahmadabotrab/Desktop/multimediaProject/src/", "images/new_image22.png","png");

    }
}
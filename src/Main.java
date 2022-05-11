import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        ProcessOnImage processOnImage = new ProcessOnImage();
        //read image
        BufferedImage image = processOnImage.readImage("Unknown.png","/Users/ahmadabotrab/Desktop/multimediaProject/src/");
        processOnImage.rgb2gray(image);
        processOnImage.writeImage(image,"/Users/ahmadabotrab/Desktop/multimediaProject/src/","new_image22.png","png");

    }
}

//        int width = image.getWidth();
//        int height = image.getHeight();
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                int p = image.getRGB(j, i);
//                int a = (p >> 24) & 0xff;
//                int r = (p >> 16) & 0xff;
//                int g = (p >> 8) & 0xff;
//                int b = (p) & 0xff;
//
//                int avg = (r + g + b) / 3;
//                p = (a << 24) | (avg << 16) | (avg << 8) | avg;
//                image.setRGB(j, i, p);
//
//            }
//            System.out.println();
//        }
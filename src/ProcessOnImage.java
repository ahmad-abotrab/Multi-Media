import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProcessOnImage {
    public void writeImage(BufferedImage image, String path, String nameOfImage, String type) {
        try {
            File file = new File(path + nameOfImage);
            ImageIO.write(image, type, file);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
    }

    public BufferedImage readImage(String nameOfImage, String path) {
        BufferedImage image = null;
        try {
            File file = new File(path+nameOfImage);
            image = ImageIO.read(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        return image;
    }

    public Pair<Integer,Integer> getDimension(BufferedImage image){
        Pair<Integer,Integer> pair = new Pair<>();
        pair.setFirst(image.getHeight()); // height
        pair.setSecond(image.getWidth()); // width
        return pair;
    }

    public ArrayList<Integer> getARGB(int valueRGB){
        ArrayList<Integer> integersValue = new ArrayList<>();
        int a = (valueRGB >> 24) & 0xff;
        int r = (valueRGB >> 16) & 0xff;
        int g = (valueRGB >> 8) & 0xff;
        int b = (valueRGB) & 0xff;
        integersValue.add(a);
        integersValue.add(r);
        integersValue.add(g);
        integersValue.add(b);
        return integersValue;
    }

    public  BufferedImage rgb2gray(BufferedImage image) {
        BufferedImage newImage = image;
        Pair<Integer,Integer> pairDim = getDimension(newImage);
        /*
            ** first is height
            ** second is width
        */
        System.out.println(pairDim.getFirst() + " " +pairDim.getSecond());
        for (int i = 0; i < pairDim.getFirst(); i++) {
            for (int j = 0; j < pairDim.getSecond(); j++) {
                int pixel = newImage.getRGB(j,i);
                ArrayList<Integer> integers = getARGB(pixel);
                /*
                 * alpha in index 0
                 * red in index 1
                 * green in index 2
                 * blue in index 3
                 */
                int newPixel = getAvg(integers.get(0),integers.get(1),integers.get(2),integers.get(3));
                newImage.setRGB(j,i,newPixel);
            }
        }
        return newImage;
    }

    public int getAvg(Integer ...color) {
        Integer alpha = color.length >= 0 ? color[0] : 0;
        Integer red = color.length >= 1 ? color[1] : 0;
        Integer green = color.length >= 2 ? color[2] : 0;
        Integer blue = color.length >= 3 ? color[3] : 0;
//        System.out.println(alpha + " "+red + " "+green + " "+blue + " ");
        int avg = (red + green + blue) / 3;
        return (alpha << 24) | (avg << 16) | (avg << 8) | avg;
    }
}

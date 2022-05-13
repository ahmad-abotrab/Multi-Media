import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class ProcessOnImage {
    BufferedImage abstractImage;

    public void writeImage(BufferedImage image, String path, String nameOfImage, String type) {
        try {
            File file = new File(path + nameOfImage);
            ImageIO.write(image, type, file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public BufferedImage readImage(String nameOfImage, String path) {
        BufferedImage image = null;
        try {
            File file = new File(path + nameOfImage);
            image = ImageIO.read(file);
            this.abstractImage = image;
        } catch (Exception e) {
            System.out.println(e);
        }
        return image;
    }

    public Pair<Integer, Integer> getDimensionsOfImage(BufferedImage image) {
        return new Pair<>(image.getHeight(), image.getWidth());
    }

    public boolean isWhile(ColorObject colorObject) {
        return colorObject.getRed() == 255 && colorObject.getGreen() == 255 && colorObject.getBlue() == 255;
    }

    public ArrayList<Pair<ColorObject, ColorObject>> frontColor_With_backColor(ArrayList<Pair<Position, ColorObject>> colorLineWithPosition, ColorObject[][] colorObjects, int row, int col) {
        ArrayList<Pair<ColorObject, ColorObject>> result = new ArrayList<>();
        for (Pair<Position, ColorObject> lineColor : colorLineWithPosition
        ) {
            Position position = lineColor.getFirst();
            ColorObject backgroundColor = colorObjects[position.getX()][position.getY()];
            if (isWhile(lineColor.getSecond())) {
                if (!isWhile(backgroundColor)) {
                    Pair<ColorObject, ColorObject> pairColorObject = new Pair<>(backgroundColor, lineColor.getSecond());
                    result.add(pairColorObject);
                }
            } else {
                Pair<ColorObject, ColorObject> pairColorObject = new Pair<>(backgroundColor, lineColor.getSecond());
                result.add(pairColorObject);
            }
        }
        return result;
    }

    public ArrayList<Pair<Position, ColorObject>> getDifferentColors(ColorObject[][] colorObjects, int row, int col) {
        ArrayList<Pair<Position, ColorObject>> colors = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!(colorObjects[i][j].getRed() == colorObjects[i][j].getGreen() && colorObjects[i][j].getGreen() == colorObjects[i][j].getBlue() && colorObjects[i][j].getBlue() != 255)) {
                    Pair<Position, ColorObject> temp = new Pair<>();
                    temp.setFirst(new Position(i, j));
                    temp.setSecond(colorObjects[i][j]);
                    colors.add(temp);
                }
            }
        }
        return colors;
    }

    public ColorObject[][] getMatrixImage(BufferedImage image) {
        Pair<Integer, Integer> dimensions = getDimensionsOfImage(image);
        ColorObject[][] colorObjects = new ColorObject[dimensions.getFirst()][dimensions.getSecond()];
        for (int i = 0; i < dimensions.getFirst(); i++) {
            for (int j = 0; j < dimensions.getSecond(); j++) {
                int pixel = image.getRGB(j, i);
                ColorObject colorObject = getARGB(pixel);
                colorObjects[i][j] = colorObject;
            }
        }
        return colorObjects;
    }

    public ColorObject getARGB(int valueRGB) {
        int a = (valueRGB >> 24) & 0xff;
        int r = (valueRGB >> 16) & 0xff;
        int g = (valueRGB >> 8) & 0xff;
        int b = (valueRGB) & 0xff;
        return new ColorObject(a, r, g, b);
    }

    public BufferedImage rgb2gray(BufferedImage image) {

        Pair<Integer, Integer> dimensions = getDimensionsOfImage(image);
        /*
         ** first is height
         ** second is width
         */
        for (int i = 0; i < dimensions.getFirst(); i++) {
            for (int j = 0; j < dimensions.getSecond(); j++) {
                int pixel = image.getRGB(j, i);
                ColorObject colorObject = getARGB(pixel);
                /*
                 * alpha in index 0
                 * red in index 1
                 * green in index 2
                 * blue in index 3
                 */
                int newPixel = getAvg(colorObject.getAlpha(), colorObject.getRed(), colorObject.getGreen(), colorObject.getBlue());
                image.setRGB(j, i, newPixel);
            }
        }
        return image;
    }

    public int getAvg(Integer... color) {
        int alpha = color[0];
        int red = color[1];
        int green = color[2];
        int blue = color[3];
//        System.out.println(alpha + " "+red + " "+green + " "+blue + " ");
        int avg = (red + green + blue) / 3;
        return (alpha << 24) | (avg << 16) | (avg << 8) | avg;
    }
}

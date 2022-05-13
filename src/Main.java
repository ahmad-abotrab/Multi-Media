import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ProcessOnImage processOnImage = new ProcessOnImage();
        //read image
        BufferedImage basicImage = processOnImage.readImage("flower1Gray.jpeg", "src/images/");
        BufferedImage imageWithColoredLine = processOnImage.readImage("flower1Gray_withLine.jpeg", "src/images/");

        // Get matrix of Basic image without any line color
        ColorObject[][] BasicColorObjects = processOnImage.getMatrixImage(basicImage);
        Pair<Integer, Integer> dimensions_basicImage = processOnImage.getDimensionsOfImage(basicImage);

        // Get matrix of Editing image with line color
        ColorObject[][] lineColored_colorObjects = processOnImage.getMatrixImage(imageWithColoredLine);
        Pair<Integer, Integer> dimensions_lineColored = processOnImage.getDimensionsOfImage(imageWithColoredLine);

        // Get color of line in image
        ArrayList<Pair<Position, ColorObject>> pairArrayList = processOnImage.getDifferentColors(lineColored_colorObjects, dimensions_lineColored.getFirst(), dimensions_lineColored.getSecond());

        System.out.println(pairArrayList.size());

        // check if any line have while color and background is white
        ArrayList<Pair<ColorObject, ColorObject>> pairColorObjects = processOnImage.frontColor_With_backColor(pairArrayList, BasicColorObjects, dimensions_basicImage.getFirst(), dimensions_basicImage.getSecond());

        System.out.println(pairColorObjects.size());
    }
}

//        ArrayList<Pair<Position,ColorObject>> colors = processOnImage.getDifferentColors(colorObjects1, dimensions1.getFirst(), dimensions1.getSecond());
//        for (ColorObject color : colors
//        ) {
//            System.out.println(color);
//        }
//        processOnImage.writeImage(image, "/Users/ahmadabotrab/Desktop/multimediaProject/src/", "images/flower1Gray.jpeg", "jpeg");
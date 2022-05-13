import java.util.Objects;

public class ColorObject {
    int alpha;
    int red;
    int green;
    int blue;

//    public ColorObject() {
//    }

    public ColorObject(int alpha, int red, int green, int blue) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "ColorObject{" +
                "alpha=" + alpha +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorObject)) return false;
        ColorObject that = (ColorObject) o;
        return getAlpha() == that.getAlpha() && getRed() == that.getRed() && getGreen() == that.getGreen() && getBlue() == that.getBlue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAlpha(), getRed(), getGreen(), getBlue());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

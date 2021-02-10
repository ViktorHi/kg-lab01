package org.openjfx.model;

import com.vikras.Task;
import com.vikras.Tasker;
import com.vikras.color.CMYK;
import com.vikras.color.HSL;
import com.vikras.color.RGB;
import javafx.scene.paint.Color;

/**
 * class which provides methods to convert colors
 */
public class Engine {
    Color color;
    Tasker tasker;
    ColorChangedListener listener;

    public Engine(ColorChangedListener listener) {
        this.listener = listener;
        tasker = new Task();
    }

    /**
     * set current color
     * @param color javafx color
     */
    public void setColor(Color color) {
        this.color = color;
        listener.colorChanged();
    }

    /**
     * set current color
     * @param hsl hsl color
     */
    public void setColor(HSL hsl) {
        setColor(tasker.hsl2rgb(hsl));
    }

    /**
     * set current color
     * @param rgb rgb color
     */
    public void setColor(RGB rgb) {
        color = Color.rgb(rgb.red, rgb.green, rgb.blue);
        listener.colorChanged();
    }

    /**
     * set current color
     * @param cmyk cmyk color
     */
    public void setColor(CMYK cmyk) {
        setColor(tasker.cmyk2rgb(cmyk));
    }

    /**
     * return current color
     * @return javafx color
     */
    public Color getColor() {
        return color;
    }

    /**
     * return current color
     * @return rgb color
     */
    public RGB getRGBColor() {
        return new RGB((int)( color.getRed()*255), (int) (color.getGreen()*255), (int) (color.getBlue()*255));
    }

    /**
     * return current color
     * @return hsl color
     */
    public HSL getHSLColor(){
        return tasker.rgb2hsl(getRGBColor());
    }

    /**
     * return current color
     * @return cmyk color
     */
    public CMYK getCMYKColor(){
        return tasker.rgb2cmyk(getRGBColor());
    }
}

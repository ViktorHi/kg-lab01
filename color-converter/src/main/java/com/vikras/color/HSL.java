package com.vikras.color;

import com.vikras.util.MathUtils;

import java.util.Objects;

public class HSL {

    /**
     * hue between [0,360]
     * present angele between start and color in color circle
     */
    final public double hue;
    /**
     * lightness between [0,1]
     * 0.5 is clear color
     */
    final public double lightness;
    /**
     * saturation between [0,1]
     */
    final public double saturation;

    /**
     * create hsl color
     * @param hue between [0,359]
     * @param lightness between [0,1]
     * @param saturation between [0,1]
     */
    public HSL(double hue, double lightness, double saturation) {
        this.hue = hue;
        this.lightness = lightness;
        this.saturation = saturation;
    }

    /**
     * create hls color from rgb
     * @param rgb rgb color
     */
    public HSL(RGB rgb) {
        var red = rgb.red / 255.0;
        var green = rgb.green / 255.0;
        var blue = rgb.blue / 255.0;
        var cMax = MathUtils.getMaxThree(red, green, blue);
        var cMin = MathUtils.getMinThree(red, green, blue);
        var delta = cMax - cMin;
        var lightness = (cMax + cMin) / 2.0;
        var saturation = delta == 0 ?
                0 :
                (delta / (1 - Math.abs(2 * lightness - 1)));
        var hue = countHue(red, green, blue, delta, cMax);
        this.hue = hue<0? hue +360: hue;
        this.lightness = lightness;
        this.saturation = saturation;

    }

    /**
     * count hls hue with using formula from wikipedia
     * @param red between [0,1]
     * @param green between [0,1]
     * @param blue between [0,1]
     * @param delta delta between max and min rgb
     * @param cMax max rgb
     * @return hue value [0,360]
     */
    private double countHue(double red, double green, double blue, double delta, double cMax) {
        if (delta == 0) return 0;
        if (red == cMax) return 60 * (((green - blue) / delta) + 0);
        if (green == cMax) return 60 * (((blue - red) / delta) + 2);
        if (blue == cMax) return 60 * (((red - green) / delta) + 4);
        throw new RuntimeException("Unreachable position was achieved");
    }

    @Override
    public String toString() {
        return "HSL{" +
                "hue=" + hue +
                ", lightness=" + lightness +
                ", saturation=" + saturation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HSL hsl = (HSL) o;
        return Double.compare(hsl.hue, hue) == 0 && Double.compare(hsl.lightness, lightness) == 0 && Double.compare(hsl.saturation, saturation) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hue, lightness, saturation);
    }
}

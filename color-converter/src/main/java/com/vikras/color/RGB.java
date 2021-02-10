package com.vikras.color;

import java.util.Objects;

public class RGB {
    public final int red;
    public final int green;
    public final int blue;

    /**
     * create rgb from cmyk
     * @param cmyk cmyk color
     */
    public RGB(CMYK cmyk) {
        red = (int) (255 * (1 - cmyk.cyan) * (1 - cmyk.key));
        green = (int) (255 * (1 - cmyk.magenta) * (1 - cmyk.key));
        blue = (int) (255 * (1 - cmyk.yellow) * (1 - cmyk.key));
    }

    /**
     * create rgb from hls color
     * @param hsl hsl color
     */
    public RGB(HSL hsl) {
        if((hsl.hue>= 360 || hsl.hue <0) || (hsl.saturation>1 || hsl.saturation<0) || (hsl.lightness<0 || hsl.lightness>1))
            throw new RuntimeException("hsl out of diapason expected 0 ≤ H < 360, 0 ≤ S ≤ 1 and 0 ≤ L ≤ 1 actual=" + hsl);
        var chroma = (1 - Math.abs(2 * hsl.lightness - 1)) * hsl.saturation;
        var h1 = hsl.hue / 60.0;
        var x = chroma * (1 - Math.abs((h1 % 2 - 1)));
        var rgb1 = getGapPoint(chroma, x, hsl.hue);
        var m = hsl.lightness - chroma / 2;
        red = (int) (rgb1.red + m*255);
        green = (int) (rgb1.green + m*255);
        blue = (int) (rgb1.blue + m*255);
    }

    /**
     * create rgb from r g b values
     * @param red between [0,255]
     * @param green between [0,255]
     * @param blue between [0,255]
     */
    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     *
     * @param c chroma value
     * @param x s value
     * @param h1 normalized hue
     * @return
     */
    private RGB getGapPoint(double c, double x, double h1) {
        if (Double.isNaN(h1)) return new RGB(0, 0, 0);
        if (h1 >= 0 && h1 <= 60)    return new RGB((int)(255 * c), (int)(255 * x), (int)(255 * 0));
        if (h1 >= 60 && h1 <= 120)  return new RGB((int)(255 * x), (int)(255 * c), (int)(255 * 0));
        if (h1 >= 120 && h1 <= 180) return new RGB((int)(255 * 0), (int)(255 * c), (int)(255 * x));
        if (h1 >= 180 && h1 <= 240) return new RGB((int)(255 * 0), (int)(255 * x), (int)(255 * c));
        if (h1 >= 240 && h1 <= 300) return new RGB((int)(255 * x), (int)(255 * 0), (int)(255 * c));
        if (h1 >= 300 && h1 <= 360) return new RGB((int)(255 * c), (int)(255 * 0), (int)(255 * x));
        throw new RuntimeException("Not expected h1 value h1="+ h1+ " expected [0,6]");
    }

    @Override
    public String toString() {
        return "RGB{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RGB rgb = (RGB) o;
        return red == rgb.red && green == rgb.green && blue == rgb.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }
}

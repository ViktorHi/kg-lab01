package com.vikras.color;

import com.vikras.util.MathUtils;

import java.util.Objects;

public class CMYK {
    final public double cyan;
    final public double magenta;
    final public double yellow;
    final public double key;

    /**
     * create cmyk color from rgb
     * @param rgb rgb color not null
     */
    public CMYK(RGB rgb) {
        key = MathUtils.getMinThree(1 - rgb.red / 255.0, 1 - rgb.green / 255.0, 1 - rgb.blue / 255.0);
        yellow =    (1 - rgb.blue   / 255.0 - key) / (1 - key);
        cyan =      (1 - rgb.red    / 255.0 - key) / (1 - key);
        magenta =   (1 - rgb.green  / 255.0 - key) / (1 - key);

    }

    /**
     * create cmyk color
     * @param cyan between [0,1]
     * @param magenta between [0,1]
     * @param yellow between [0,1]
     * @param key between [0,1]
     */
    public CMYK(double cyan, double magenta, double yellow, double key) {
        this.cyan = cyan;
        this.magenta = magenta;
        this.yellow = yellow;
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMYK cmyk = (CMYK) o;
        return Double.compare(cmyk.cyan, cyan) == 0 && Double.compare(cmyk.magenta, magenta) == 0 && Double.compare(cmyk.yellow, yellow) == 0 && Double.compare(cmyk.key, key) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyan, magenta, yellow, key);
    }

    @Override
    public String toString() {
        return "CMYK{" +
                "cyan=" + cyan +
                ", magenta=" + magenta +
                ", yellow=" + yellow +
                ", key=" + key +
                '}';
    }
}

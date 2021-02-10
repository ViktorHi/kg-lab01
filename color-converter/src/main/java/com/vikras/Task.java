package com.vikras;

import com.vikras.color.CMYK;
import com.vikras.color.HSL;
import com.vikras.color.RGB;

public class Task implements Tasker{
    @Override
    public CMYK rgb2cmyk(RGB rgb) {
        return new CMYK(rgb);
    }

    @Override
    public RGB cmyk2rgb(CMYK cmyk) {
        return new RGB(cmyk);
    }

    @Override
    public HSL rgb2hsl(RGB rgb) {
        return new HSL(rgb);
    }

    @Override
    public RGB hsl2rgb(HSL hsl) {
        return new RGB(hsl);
    }

    @Override
    public CMYK hsl2cmyk(HSL hsl) {
        return new CMYK(new RGB(hsl));
    }

    @Override
    public HSL cmyk2hsl(CMYK cmyk) {
        return new HSL(new RGB(cmyk));
    }
}

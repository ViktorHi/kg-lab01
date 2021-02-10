package com.vikras;

import com.vikras.color.CMYK;
import com.vikras.color.HSL;
import com.vikras.color.RGB;

public interface Tasker {

    /**
     * convert rgb to cmyk
     * @param rgb rgb color
     * @return cmyk color
     */
    CMYK rgb2cmyk(RGB rgb);

    /**
     * convert cmyk to rgb
     * @param cmyk cmyk color
     * @return rgb color
     */
    RGB cmyk2rgb(CMYK cmyk);

    /**
     * convert rgb to hsl
     * @param rgb rgb color
     * @return cmyk color
     */
    HSL rgb2hsl(RGB rgb);

    /**
     * convert hsl to rgb
     * @param hsl hsl color
     * @return rgb color
     */
    RGB hsl2rgb(HSL hsl);

    /**
     * convert hsl to rgb
     * @param hsl hsl color
     * @return cmyk color
     */
    CMYK hsl2cmyk(HSL hsl);

    /**
     * convert cmyk to hsl
     * @param cmyk cmyl color
     * @return hsl color
     */
    HSL cmyk2hsl(CMYK cmyk);

}

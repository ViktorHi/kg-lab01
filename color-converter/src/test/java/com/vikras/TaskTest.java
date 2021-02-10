package com.vikras;

import com.vikras.color.HSL;
import com.vikras.color.RGB;
import org.junit.Assert;
import org.junit.Test;



public class TaskTest {

    @Test
    public void rgb2hsl() {
//        var rgb = new RGB(20, 145, 12);
//        var hsl = new HSL(116, .308, .847);
        var rgb = new RGB(255, 0, 153);
        var hsl = new HSL(300, .50, 1.00);
//        var rgb = new RGB(70, 120, 0);
//        var hsl = new HSL(85, 0.235, 0.100);

        var task = new Task();
        HSL hsl1 = task.rgb2hsl(rgb);
        //Assert.assertTrue(true);
//        Assert.assertEquals(hsl, hsl1);
    }


    @Test
    public void hsl2rgb() {
//        var rgb = new RGB(70, 120, 0);
//        var hsl = new HSL(85, .235, 1.00);

        var rgb = new RGB(20, 145, 12);
        var hsl = new HSL(116, .308, .847);

        var task = new Task();
        RGB rgb1 = task.hsl2rgb(hsl);
        Assert.assertEquals(rgb, rgb1);
    }


    @Test
    public void hsl2cmyk() {
    }


    @Test
    public void cmyk2hsl() {
    }
}
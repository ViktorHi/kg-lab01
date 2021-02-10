package org.openjfx.controller;

import com.vikras.color.CMYK;
import com.vikras.color.HSL;
import com.vikras.color.RGB;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.openjfx.model.Engine;
import org.openjfx.model.ColorChangedListener;


public class MainController implements ColorChangedListener {

    //region field
    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField tfRed;

    @FXML
    private Slider slRed;

    @FXML
    private TextField tfGreen;

    @FXML
    private Slider slGreen;

    @FXML
    private TextField tfBlue;

    @FXML
    private Slider slBlue;

    @FXML
    private TextField tfHue;

    @FXML
    private Slider slHue;

    @FXML
    private TextField tfLightness;

    @FXML
    private Slider slLightness;

    @FXML
    private TextField tfSaturation;

    @FXML
    private Slider slSaturation;

    @FXML
    private TextField tfCyan;

    @FXML
    private Slider slCyan;

    @FXML
    private TextField tfMagenta;

    @FXML
    private Slider slMagenta;

    @FXML
    private TextField tfYellow;

    @FXML
    private Slider slYellow;

    @FXML
    private TextField tfKey;

    @FXML
    private Slider slKey;

    @FXML
    private Label infoLabel;
//    endregion

    /**
     * provides convert methods
     */
    private Engine engine;

    /**
     * method must be run to init all gui components
     */
    public void init() {
        colorPicker.setOnAction(this::colorPickerHandler);
        engine = new Engine(this);
        initSliders();
        engine.setColor(Color.WHITE);
        initTextFields();

    }

    /**
     * init all sliders
     */
    private void initSliders() {
        initSlider(slRed, 0, 255, 25, 3);
        initSlider(slGreen, 0, 255, 25, 3);
        initSlider(slBlue, 0, 255, 25, 3);

        initSlider(slHue, 0, 359, 25, 3);
        initSlider(slSaturation, 0, 1, 0.1, 9);
        initSlider(slLightness, 0, 1, 0.1, 9);

        initSlider(slCyan, 0, 1, 0.1, 9);
        initSlider(slMagenta, 0, 1, 0.1, 9);
        initSlider(slYellow, 0, 1, 0.1, 9);
        initSlider(slKey, 0, 1, 0.1, 9);
    }

    /**
     * init max and min slider values, enable tick labels and marks add event handler
     *
     * @param slider slider
     * @param min    min position
     * @param max    max position
     * @param unit   amounts of units
     * @param count  count of ticks
     */
    private void initSlider(Slider slider, double min, double max, double unit, int count) {
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMax(max);
        slider.setMin(min);
        slider.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::sliderHandler);
        slider.setMajorTickUnit(unit);
        slider.setMinorTickCount(count);
    }

    /**
     * add handler to text fields
     */
    private void initTextFields() {
        tfRed       .setOnKeyPressed(this::textFieldHandler);
        tfGreen     .setOnKeyPressed(this::textFieldHandler);
        tfBlue      .setOnKeyPressed(this::textFieldHandler);
        tfHue       .setOnKeyPressed(this::textFieldHandler);
        tfLightness .setOnKeyPressed(this::textFieldHandler);
        tfSaturation.setOnKeyPressed(this::textFieldHandler);
        tfCyan      .setOnKeyPressed(this::textFieldHandler);
        tfMagenta   .setOnKeyPressed(this::textFieldHandler);
        tfKey       .setOnKeyPressed(this::textFieldHandler);
        tfYellow    .setOnKeyPressed(this::textFieldHandler);
    }

    /**
     * handle text fields changes
     *
     * @param keyEvent
     */
    private void textFieldHandler(KeyEvent keyEvent) {
        TextField source = (TextField) keyEvent.getSource();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                switch (source.getId()) {
                    case "tfRed", "tfGreen", "tfBlue" -> engine.setColor(getRGBTextFieldColor());
                    case "tfHue", "tfLightness", "tfSaturation" -> engine.setColor(getHSLTextFieldColor());
                    case "tfCyan", "tfMagenta", "tfYellow", "tfKey" -> engine.setColor(getCMYKTextFieldColor());
                    default -> throw new RuntimeException("unrecognized id " + source.getId());
                }
            } catch (RuntimeException e) {
                infoLabel.setText(e.getMessage());
            }
        }
    }


    /**
     * handle sliders changes
     *
     * @param t   event
     * @param <T> event
     */
    private <T extends Event> void sliderHandler(T t) {
        Slider source = (Slider) t.getSource();
        switch (source.getId()) {
            case "slRed", "slGreen", "slBlue" -> engine.setColor(getRGBSliderColor());
            case "slHue", "slLightness", "slSaturation" -> engine.setColor(getHSLSliderColor());
            case "slCyan", "slMagenta", "slYellow", "slKey" -> engine.setColor(getCMYKSliderColor());
            default -> throw new RuntimeException("unrecognized id " + source.getId());
        }
        ;
    }


    /**
     * handle color picker handler
     *
     * @param actionEvent source
     */
    private void colorPickerHandler(javafx.event.ActionEvent actionEvent) {
        Color color = colorPicker.getValue();
        engine.setColor(color);
    }


    @Override
    public void colorChanged() {
        infoLabel.setText("");
        updateCMYKView(engine.getCMYKColor());
        updateRGBView(engine.getRGBColor());
        updateHSLView(engine.getHSLColor());
        colorPicker.setValue(engine.getColor());
    }

    /**
     * upgrade all rgb views
     *
     * @param rgb rgb color
     */
    private void updateRGBView(RGB rgb) {
        tfRed           .setText(String.valueOf(rgb.red));
        tfGreen         .setText(String.valueOf(rgb.green));
        tfBlue          .setText(String.valueOf(rgb.blue));

        slRed           .setValue(rgb.red);
        slGreen         .setValue(rgb.green);
        slBlue          .setValue(rgb.blue);
    }

    /**
     * update all hsl views
     *
     * @param hsl hsl color
     */
    private void updateHSLView(HSL hsl) {
        tfHue           .setText(String.format("%.4f",hsl.hue));
        tfSaturation    .setText(String.format("%.4f",hsl.saturation));
        tfLightness     .setText(String.format("%.4f",hsl.lightness));

        slHue           .setValue(hsl.hue);
        slSaturation    .setValue(hsl.saturation);
        slLightness     .setValue(hsl.lightness);
    }

    /**
     * update cmyk views
     *
     * @param cmyk cmyk color
     */
    private void updateCMYKView(CMYK cmyk) {
        tfCyan      .setText(String.format("%.4f", cmyk.cyan));
        tfMagenta   .setText(String.format("%.4f", cmyk.magenta));
        tfYellow    .setText(String.format("%.4f", cmyk.yellow));
        tfKey       .setText(String.format("%.4f", cmyk.key));

        slCyan      .setValue(cmyk.cyan);
        slMagenta   .setValue(cmyk.magenta);
        slYellow    .setValue(cmyk.yellow);
        slKey       .setValue(cmyk.key);
    }

    /**
     * get color from rgb slider
     *
     * @return javafx color
     */
    private Color getRGBSliderColor() {
        return Color.rgb((int) slRed.getValue(), (int) slGreen.getValue(), (int) slBlue.getValue());
    }

    /**
     * get color from hsl slider
     *
     * @return hsl color
     */
    private HSL getHSLSliderColor() {
        return new HSL(slHue.getValue(), slLightness.getValue(), slSaturation.getValue());
    }

    /**
     * get cmyk color form cmyk slider
     *
     * @return cmyk color
     */
    private CMYK getCMYKSliderColor() {
        return new CMYK(slCyan.getValue(), slMagenta.getValue(), slYellow.getValue(), slKey.getValue());
    }

    /**
     * get rgb color from rgb tf
     *
     * @return rgb color
     */
    private RGB getRGBTextFieldColor() {
        var red = (int) parse(tfRed, 0, 255);
        var green = (int) parse(tfGreen, 0, 255);
        var blue = (int) parse(tfBlue, 0, 255);
        return new RGB(red, green, blue);
    }

    /**
     * get cmyk color from cmyk tf
     *
     * @return cmyk color
     */
    private CMYK getCMYKTextFieldColor() {
        var cyan = parse(tfCyan, 0, 1);
        var magenta = parse(tfMagenta, 0, 1);
        var yellow = parse(tfYellow, 0, 1);
        var key = parse(tfKey, 0, 1);
        return new CMYK(cyan, magenta, yellow, key);
    }

    /**
     * get hsl color from hsl tf
     *
     * @return hsl color
     */
    private HSL getHSLTextFieldColor() {
        var hue = parse(tfHue, 0, 359);
        var s = parse(tfSaturation, 0, 1);
        var l = parse(tfLightness, 0, 1);
        return new HSL(hue, l, s);
    }

    /**
     * parse tf with min max values throw illegal argument exception
     *
     * @param textField tf
     * @param min       min value
     * @param max       max value
     * @return double value
     */
    private double parse(TextField textField, double min, double max) {
        String text = textField.getText();
        double v = 0;
        try {
            v = Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("illegal color value " + text);
        }
        if (v < min || v > max)
            throw new IllegalArgumentException("illegal color value " + text + " should be between [" + min + "," + max + "]");
        return v;
    }


}

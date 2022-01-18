package gui.painters;

import gui.convertion.ConvertPlane;
import gui.convertion.Converter;

import java.awt.*;

/**
 * Рисовальщик декартовой плоскости
 * */
public class CartesianPainter extends Painter{
    public CartesianPainter(ConvertPlane plane) {
        // передача plane в конструктор родительского класса Painter
        super(plane);
    }

    @Override
    public void draw(Graphics graphics) {
        drawAxes(graphics); // рисование осей X и Y
        drawTicks(graphics); // рисование делений на осях X и Y
        drawNumbers(graphics); // рисование чисел вдоль осей X и Y
    }

    // рисование осей X и Y
    private void drawAxes(Graphics graphics){
        var axesColor = Color.GRAY; // цвет осей
        if (graphics != null) {
            graphics.setColor(axesColor); // установка цвета рисования
            // рисование оси X
            graphics.drawLine(Converter.xCrt2Scr(_plane.getXMin(), _plane), Converter.yCrt2Scr(0, _plane),
                    Converter.xCrt2Scr(_plane.getXMax(), _plane), Converter.yCrt2Scr(0, _plane));
            //рисование оси Y
            graphics.drawLine(Converter.xCrt2Scr(0, _plane), Converter.yCrt2Scr(_plane.getYMax(), _plane),
                    Converter.xCrt2Scr(0, _plane), Converter.yCrt2Scr(_plane.getYMin(), _plane));
        }
    }

    //region Рисование делений

    // рисование делений на осях X и Y
    private void drawTicks(Graphics graphics){
        if(graphics == null){
            return;
        }
        int df = 2; // коэффициент смещения (необходимо для рисования делений)
        drawXTicks(graphics, df); // рисование делений на оси X
        drawYTicks(graphics, df); // рисование делений на оси Y
    }

    private void drawXTicks(Graphics graphics, int bias){
        var y0 = Converter.yCrt2Scr(0., _plane);
        for (long i = (int) (_plane.getXMin() * 10); i < (int) (_plane.getXMax() * 10); i++) {
            var h = 2;
            if (i % 10 == 0) {
                graphics.setColor(Color.RED);
            } else if (i % 5 == 0) {
                h = 1;
                graphics.setColor(Color.BLUE);
            } else {
                graphics.setColor(Color.DARK_GRAY);
                h = 0;
            }
            graphics.drawLine(Converter.xCrt2Scr(i / 10., _plane), y0 + bias + h,
                    Converter.xCrt2Scr(i / 10., _plane), y0 - bias - h);
        }
    }

    private void drawYTicks(Graphics graphics, int bias){
        var x0 = Converter.xCrt2Scr(0., _plane);
        for (long i = (int) (_plane.getYMin() * 10); i < (int) (_plane.getYMax() * 10); i++) {
            var h = 2;
            if (i % 10 == 0) {
                graphics.setColor(Color.RED);
            } else if (i % 5 == 0) {
                h = 1;
                graphics.setColor(Color.BLUE);
            } else {
                graphics.setColor(Color.DARK_GRAY);
                h = 0;
            }
            graphics.drawLine(x0 + bias + h, Converter.yCrt2Scr(i / 10., _plane),
                    x0 - bias - h, Converter.yCrt2Scr(i / 10., _plane));
        }
    }
    //endregion

    //region Рисование чисел

    // рисование чисел вдоль осей X и Y
    private void drawNumbers(Graphics graphics){
        if(graphics == null){
            return;
        }
        graphics.setColor(Color.MAGENTA);
        drawXNumbers(graphics); // рисование чисел на оси X
        drawYNumbers(graphics); // рисование чисел на оси Y
    }

    private void drawXNumbers(Graphics graphics){
        var y0 = Converter.yCrt2Scr(0., _plane);
        for (long i = (int) (_plane.getXMin() * 10); i < (int) (_plane.getXMax() * 10); i++) {
            if (i == 0 || i % 5 != 0)
                continue;
            var h = -10;
            if (i > 0)
                h = 20;
            var p = i > 0 ? -6 : -10;
            graphics.drawString(String.valueOf(i / 10.), Converter.xCrt2Scr(i / 10., _plane) + p, y0 + h);
        }
    }

    private void drawYNumbers(Graphics graphics){
        var x0 = Converter.xCrt2Scr(0., _plane);
        for (long i = (int) (_plane.getYMin() * 10); i < (int) (_plane.getYMax() * 10); i++) {
            if (i == 0 || i % 5 != 0)
                continue;
            var h = -30;
            if (i > 0)
                h = 10;
            graphics.drawString(String.valueOf(i / 10.), x0 + h, Converter.yCrt2Scr(i / 10., _plane) + 4);
        }
    }
    //endregion
}

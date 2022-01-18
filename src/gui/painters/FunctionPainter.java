package gui.painters;

import functions.interfaces.IExplicitFunction;
import functions.interfaces.IFunction;
import functions.interfaces.IParametricFunction;
import gui.convertion.ConvertPlane;
import gui.convertion.Converter;

import java.awt.*;
import java.math.BigInteger;

/**
 * Рисовальщик функции
 * */
public class FunctionPainter extends Painter{

    private IFunction _targetFunction; // функция, которую необходимо нарисовать

    public FunctionPainter(ConvertPlane plane) {
        super(plane);
    }

    /**
     * Установка функции, которую необходимо нарисовать
     * */
    public FunctionPainter setFunction(IFunction function){
        _targetFunction = function;
        return this;
    }

    @Override
    public void draw(Graphics graphics) {
        var step = 0.001; // шаг сдвига точки
        drawLines(graphics, step); // рисование линии функции
    }

    // рисование линии функции
    private void drawLines(Graphics graphics, double step){
        var graphxColor = Color.RED; // цвет линии графика
        if(graphics == null){
            return;
        }
        if(_targetFunction == null){ // если нечего рисовать, то ничего рисовать и не будем
            return;
        }
        graphics.setColor(graphxColor);
        if(_targetFunction instanceof IExplicitFunction){ // проверка является тип функции _targetFunction типом IExplicitFunction (явной функции)
            drawExplicitFunction(graphics, step); // используем метод рисования явной функции
        }
        else if(_targetFunction instanceof IParametricFunction){ // проверка является тип функции _targetFunction типом IParametricFunction (параметрической функции)
            drawParametricFunction(graphics, step); // используем метод рисования параметрической функции
        }
    }

    private void drawExplicitFunction(Graphics graphics, double step){
        // приводим тип IFunction к типу IExplicitFunction (данное приведение уместно, ибо до вызова была произведена проверка типа),
        // иными словми говорим, что некоторая функция типа IFunction является явной функцией типа IExplicitFunction
        var function = (IExplicitFunction)_targetFunction;
        var x = _plane.getXMin(); // точка x, которая будет двигаться по оси X с шагом step
        var xMax = _plane.getXMax(); // максимальное значение точки x
        while(x < xMax){
            var currentY = function.getY(x); // значение функции в точке x
            var nextY = function.getY(x + step); // значение функции в точке x + step
            if(isFinite(currentY) && isFinite(nextY)){ // проверка значений функций на бесконечность
                graphics.drawLine(
                        Converter.xCrt2Scr(x, _plane), Converter.yCrt2Scr(currentY, _plane),
                        Converter.xCrt2Scr(x + step, _plane), Converter.yCrt2Scr(nextY, _plane)
                ); // рисование отрезка между значениями функции в точках x и x + step
            }
            x += step; // двигаем точку дальше
        }
    }

    private void drawParametricFunction(Graphics graphics, double step){
        // приводим тип IFunction к типу IParametricFunction (данное приведение уместно, ибо до вызова была произведена проверка типа),
        // иными словми говорим, что некоторая функция типа IFunction является параметрической функцией типа IParametricFunction
        var function = (IParametricFunction)_targetFunction;

        // в условиях задания не указано никаких условий для параметра t, в таком случае эти условия можно задать самим
        // условимся на том, что t лежит на отрезке [0; 10]
        double t = 0.0; // точка t, которая будет двигаться по отрезку [0; 10] с шагом step
        double maxT = 10.0; // максимальное значение точки t
        while(t < maxT){
            var currX = function.getX(t); // значение функции X в точке t
            var currY = function.getY(t); // значение функции Y в точке t
            var nextX = function.getX(t + step); // значение функции X в точке t + step
            var nextY = function.getY(t + step); // значение функции Y в точке t + step
            if (isFinite(currX) && isFinite(nextX) && isFinite(currY) && isFinite(nextY)){ // проверка значений функций на бесконечность
                graphics.drawLine(
                        Converter.xCrt2Scr(currX, _plane), Converter.yCrt2Scr(currY, _plane),
                        Converter.xCrt2Scr(nextX, _plane), Converter.yCrt2Scr(nextY, _plane)
                );// рисование отрезка между значениями функций X и Y в точках t и t + step
            }
            t += step;
        }
    }

    // проверка числа на бесконечность (пришлось изобрести собственную бесконечность для корректного рисования)
    private boolean isFinite(double number){
        double infinity = 90000000000000.; // искусственная бесконечность
        return Math.abs(number) < infinity;
    }
}

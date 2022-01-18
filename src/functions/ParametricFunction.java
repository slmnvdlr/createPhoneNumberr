package functions;

import functions.interfaces.*;

// класс реализующий интерфейс IParametricFunction, т.е. является прямой реализацией некоторой функции, заданной параметрически
public class ParametricFunction implements IParametricFunction {

    @Override
    public double getX(double t) {
        return t*Math.sin(t)+Math.cos(t);
    }

    @Override
    public double getY(double t) {
        return Math.sin(t)-t*Math.cos(t);
    }
}

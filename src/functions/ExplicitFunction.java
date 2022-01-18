package functions;

import functions.interfaces.IExplicitFunction;

// класс реализующий интерфейс IExplicitFunction, т.е. является прямой реализацией некоторой функции, заданной явно
public class ExplicitFunction implements IExplicitFunction {
    @Override
    public double getY(double x) {
        return (2) / (x*x + 1);
    }
}

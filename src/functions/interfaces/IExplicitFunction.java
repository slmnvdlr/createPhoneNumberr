package functions.interfaces;

/**
 * Интерфейс, характеризующий явную функцию
 * */
public interface IExplicitFunction extends IFunction{
    /**
     * Получить значение функции
     * @param x аргумент функции
     * @return значение функции
     * */
    public double getY(double x);
}

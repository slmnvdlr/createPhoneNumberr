package functions.interfaces;

/**
 * Интерфейс, характеризующий параметрическую функцию
 * */
public interface IParametricFunction extends IFunction{
    /**
     * Получить значение функции X
     * @param t аргумент функции
     * @return значение функции X
     * */
    public double getX(double t);

    /**
     * Получить значение функции Y
     * @param t аргумент функции
     * @return значение функции Y
     * */
    public double getY(double t);
}

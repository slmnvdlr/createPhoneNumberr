package gui.convertion;

/**
 * Конвертер между декартовыми и экранными координатами
 */
public class Converter {
    /**
     * Конвертация координаты x из декартовой системы координат в экранную
     * @param x координата x
     * @param plane контекст декартовой плоскости
     * @return координата x в экранной системе координат
     */
    public static int xCrt2Scr(double x, ConvertPlane plane) {
        var px = plane.getXDen();
        return (int)(px * (x - plane.getXMin()));
    }

    /**
     * Конвертация координаты x из экранной системы координат в декартовую
     * @param x координата x
     * @param plane контекст декартовой плоскости
     * @return координата x в декартовой системе координат
     */
    public static double xScr2Crt(double x, ConvertPlane plane) {
        var px = plane.getXDen();
        return x * (1.0 / px) + plane.getXMin();
    }

    /**
     * Конвертация координаты y из декартовой системы координат в экранную
     * @param y координата y
     * @param plane контекст декартовой плоскости
     * @return координата y в экранной системе координат
     */
    public static int yCrt2Scr(double y, ConvertPlane plane) {
        var py = plane.getYDen();
        return (int)(py * (plane.getYMax() - y));
    }

    /**
     * Конвертация координаты y из экранной системы координат в декартовую
     * @param y координата y
     * @param plane контекст декартовой плоскости
     * @return координата y в декартовой системе координат
     */
    public static double yScr2Crt(double y, ConvertPlane plane) {
        var py = plane.getYDen();
        return (-y) * (1.0 / py) + plane.getYMax();
    }
}

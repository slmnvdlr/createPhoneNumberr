package gui.convertion;

import gui.convertion.args.PlaneArgs;

/**
 * Класс, предоставляющий удобное управление контекстом декартовой плоскости {@link ConvertPlane}
 * */
public class PlaneManager {
    private static ConvertPlane _plane;

    /**
     * Стандартная инициализация контекста декартовой плоскости {@link ConvertPlane}
     * поля объекта {@link ConvertPlane} инициализируются следующим образом
     * xMin = -5
     * xMax = 5
     * yMin = -5
     * yMax = 5
     * @param width ширина экрана
     * @param height высота экрана
     * */
    public static void init(int width, int height){
        initPlane(width, height);
    }

    /**
     * Пользовательская инициализация контекста декартовой плоскости {@link ConvertPlane}
     */
    public static void initCustom(int width, int height, PlaneArgs args){
        initCustomPlane(width, height, args);
    }

    /**
     * Геттер для контекста декартовой плоскости {@link ConvertPlane}
     * */
    public static ConvertPlane getPlane() throws NullPointerException{
        if(_plane == null){   // данная проверка необходима, чтобы избежать дальнейшего использования пустой ссылки
            throw new NullPointerException("ConvertPlane не был инициализирован");
        }
        return _plane;
    }

    /**
     * Обновление размеров экрана в контексте декартовой плоскости {@link ConvertPlane}
     * @param width новая ширина экрана
     * @param height новая высота экрана
     * */
    public static void updatePlaneSize(int width, int height){
        _plane.setRealWidth(width);
        _plane.setRealHeight(height);
    }

    /**
     * Обновление параметров контекста декартовой плоскости {@link ConvertPlane}
     * @param args новые параметры контекста декартовой плоскости
     * */
    public static void updatePlaneArgs(PlaneArgs args){
        _plane.setXMin(args.getXMin());
        _plane.setXMax(args.getXMax());
        _plane.setYMin(args.getYMin());
        _plane.setYMax(args.getYMax());
    }

    // Реализация стандартной инициализации контекста декартовой плоскости
    private static void initPlane(int width, int height){
        var args = new PlaneArgs(
                -5, 5, -5, 5
        );
        _plane = new ConvertPlane(width, height, args);
    }

    // Реализация пользовательской инициализации контекста декартовой плоскости
    private static void initCustomPlane(int width, int height, PlaneArgs args){
        _plane = new ConvertPlane(width, height, args);
    }
}

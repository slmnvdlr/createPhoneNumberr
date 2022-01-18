package gui.convertion.args;

/**
 * Класс для компактной передачи параметров декартовой плоскости
 * */
public class PlaneArgs {
    private final double _xMin;
    private final double _xMax;
    private final double _yMin;
    private final double _yMax;

    public PlaneArgs(double xMin, double xMax, double yMin, double yMax){
        _xMin = xMin;
        _xMax = xMax;
        _yMin = yMin;
        _yMax = yMax;
    }

    public double getXMin(){
        return _xMin;
    }
    public double getXMax(){
        return _xMax;
    }
    public double getYMin(){
        return _yMin;
    }
    public double getYMax(){
        return _yMax;
    }
}

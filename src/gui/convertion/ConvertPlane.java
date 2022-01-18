package gui.convertion;

import gui.convertion.args.PlaneArgs;

/**
 * Контекст декартовой плоскости
 * */
public class ConvertPlane {

    private int realWidth;
    private int realHeight;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    /**
     * @param realWidth ширина экрана
     * @param realHeight высота экрана
     * @param args параметры декартовой плоскости
     * */
    public ConvertPlane(int realWidth, int realHeight, PlaneArgs args) {
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.xMin = args.getXMin();
        this.xMax = args.getXMax();
        this.yMin = args.getYMin();
        this.yMax = args.getYMax();
    }

    //region Сеттеры всех полей
    public void setXMin(double xMin){
        this.xMin = xMin;
    }
    public void setXMax(double xMax){
        this.xMax = xMax;
    }
    public void setYMin(double yMin){
        this.yMin = yMin;
    }
    public void setYMax(double yMax){
        this.yMax = yMax;
    }
    public void setRealWidth(int RealWidth){
        this.realWidth = Math.abs(RealWidth);
    }
    public void setRealHeight(int RealHeight){
        this.realHeight = Math.abs(RealHeight);
    }
    //endregion

    //region Геттеры всех полей
    public int getWidth() { return realWidth - 1; }
    public int getHeight() {
        return realHeight - 1;
    }
    public double getXMin() {
        return xMin;
    }
    public double getXMax() {
        return xMax;
    }
    public double getYMin() {
        return yMin;
    }
    public double getYMax() {
        return yMax;
    }
    //endregion

    /**
     * Отношение ширины экрана к длине обозримой оси X в текущем контексте декартовой плоскости
     * */
    public double getXDen() {
        return getWidth() / (xMax - xMin);
    }

    /**
     * Отношение высоты экрана к длине обозримой оси Y в текущем контексте декартовой плоскости
     * */
    public double getYDen() {
        return getHeight() / (yMax - yMin);
    }
}

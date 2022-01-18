package gui.painters;

import gui.convertion.ConvertPlane;

import java.awt.*;

/**
 * Класс базового рисовальщика
 * */
public abstract class Painter {
    // модификатор доступа protected, дабы дать прямой доступ к полю всем производным классам
    protected ConvertPlane _plane;

    public Painter(ConvertPlane plane){
        _plane = plane;
    }

    /**
     * Рисование относительно заданного графического контекста
     * @param graphics графический контекст
     * */
    public abstract void draw(Graphics graphics);
}

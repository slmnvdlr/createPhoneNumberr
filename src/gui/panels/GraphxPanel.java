package gui.panels;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import gui.painters.Painter;

/**
 * Панель, содержащий в себе рисунки добавленных рисовальщиков
 * */
public class GraphxPanel extends JPanel {
    // список рисовальщиков
    private final ArrayList<Painter> painters = new ArrayList<>();

    public GraphxPanel(){
        setBorder(new EtchedBorder());
        setBackground(Color.WHITE);
    }

    /**
     * Добавление рисовальщика
     * @param p добавляемый рисовальщик
     * */
    public void addPainter(Painter p){
        painters.add(p);
    }
    /**
     * Удаление рисовальщика
     * @param p удаляемый рисовальщик
     * */
    public void removePainter(Painter p){
        painters.remove(p);
    }

    /**
     * Переопределенный метод класса {@link JPanel}, вызывающийся при необходимости перерисовать панель
     * */
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics); // вызов этого же метода у родительского класса
        painters.forEach(painter -> painter.draw(graphics)); // вызов метода draw у всех добавленных рисовальщиков
    }
}

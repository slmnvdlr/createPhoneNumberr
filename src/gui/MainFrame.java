package gui;

import functions.ParametricFunction;
import gui.convertion.PlaneManager;
import gui.convertion.args.PlaneArgs;
import gui.painters.CartesianPainter;
import gui.painters.FunctionPainter;
import gui.painters.Painter;
import gui.panels.ChoosePanel;
import gui.panels.ControlPanel;
import gui.panels.GraphxPanel;
import gui.panels.listeners.IResizedListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Фрейм (или форма), внутри которой располагаются все нами добавленные компоненты (типа панели, кнопки, спиннеры и т.д.)
 * */
public class MainFrame extends JFrame {
    private final Dimension _minFrameSize;

    public MainFrame(int width, int height){
        _minFrameSize = new Dimension(width, height); // минимальный размер формы
        setDefaultCloseOperation(EXIT_ON_CLOSE); // приложение будет завершать работу при закрытии формы
        setVisible(true); // устанавливаем видимость формы на "видимый"
        setMinimumSize(_minFrameSize); // устанавливаем минимальный размер формы
        setTitle("Function GraphX"); // устанавливаем название формы (не очень важная строчка)

        // инициализируем панели
        GraphxPanel panel = new GraphxPanel();
        ControlPanel cPanel = new ControlPanel();
        ChoosePanel chPanel = new ChoosePanel();
        configureAndSetLayout(panel, cPanel, chPanel); // конфигурируем и устанавливаем для формы layout, расставляя панели в нужные места
        PlaneManager.init(panel.getWidth(), panel.getHeight()); // инициализируем контекст декартовой плоскости

        createAndAddPainters(panel); // добавляем рисовальщиков
        handleFrameEvents(panel); // обрабатываем нужные события формы
        handleControlPanel(cPanel, panel); // обрабатываем нужные события ControlPanel

        panel.repaint();
    }

    // создание и добавление рисовальщиков в панель
    private void createAndAddPainters(GraphxPanel graphxPanel){
        var painters = new Painter[] { // создаем массив рисовальщиков
                new CartesianPainter(PlaneManager.getPlane()), // инициализируем рисовальщика декартовой плоскости
                new FunctionPainter(PlaneManager.getPlane()) // инициализируем рисовальщика функции
                        .setFunction(
                                new ParametricFunction()) // у рисовальщика функции устанавливаем функцию, график которой рисовальщик будет рисовать
        };
        for (var painter : painters) { // проходимся по элементам массива рисовальщиков
            graphxPanel.addPainter(painter); // добавляем рисовальщиков в панель
        }
    }

    // конфигурирование и установка layout'a
    private void configureAndSetLayout(GraphxPanel graphxPanel, ControlPanel controlPanel, ChoosePanel choosePanel){
        GroupLayout gl = new GroupLayout(getContentPane());
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addComponent(graphxPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(5)
                .addComponent(choosePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(5)
        );
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addComponent(graphxPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(choosePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(5)
        );
        setLayout(gl);
        pack();
    }

    // обработка событий формы
    private void handleFrameEvents(GraphxPanel graphxPanel){
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) { // событие изменения размера формы
                super.componentResized(e);
                PlaneManager.updatePlaneSize(graphxPanel.getWidth(), graphxPanel.getHeight()); // обновляем размер экрана у контекста декартовой плоскости
                graphxPanel.repaint(); // перерисовываем панель
            }
        });
    }

    // обработка событий панели управления ControlPanel
    private void handleControlPanel(ControlPanel controlPanel, GraphxPanel graphxPanel){
        controlPanel.addResizedListener(new IResizedListener() { // обработка события изменения значения на спиннерах панели управления (ControlPanel)
            @Override
            public void resize() {
                PlaneArgs args = new PlaneArgs( // получаем новые параметры контекста декартовой плоскости ConvertPlane из панели управления ControlPanel
                        controlPanel.getXMin(), controlPanel.getXMax(),
                        controlPanel.getYMin(), controlPanel.getYMax()
                );
                PlaneManager.updatePlaneArgs(args); // обновляем параметры контекста декартовой плоскости ConvertPlane
                graphxPanel.repaint(); // перерисовываем панель
            }
        });
    }
}

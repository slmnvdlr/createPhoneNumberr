package gui.panels;

import gui.panels.listeners.IBaseListener;
import gui.panels.listeners.IResizedListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * Панель управления контекстом декартовой плоскости {@link gui.convertion.ConvertPlane}
 * */
public class ControlPanel extends JPanel {

    // список слушателей разных событий
    private final ArrayList<IBaseListener> listeners = new ArrayList<>();

    private final JSpinner[] spinners;

    public ControlPanel(){
        setBackground(Color.GRAY.brighter());
        setBorder(new EtchedBorder());

        spinners = createJSpinnersAndHandleEvents(); // создаем необходимые спиннеры, и вместе с этим обрабатываем их событие изменения значения
        JLabel[] labels = createLabels(); // создаем лэйблы
        configureAndSetLayout(spinners, labels); // конфигурируем layout, расставляя спиннеры и лейблы в нужные места, и устанавливаем этот layout для данной панели (ControlPanel)
    }

    /**
     * Добавить слушателя изменения значений на спиннерах панели
     * */
    public void addResizedListener(IResizedListener listener){
        listeners.add(listener);
    }

    //region Геттеры значений спиннеров
    public double getXMin() {
        return (double) spinners[0].getValue();
    }

    public double getXMax() {
        return (double) spinners[1].getValue();
    }

    public double getYMin() {
        return (double) spinners[2].getValue();
    }

    public double getYMax() {
        return (double) spinners[3].getValue();
    }
    //endregion

    //region Создание спиннеров

    // создание моделей для спиннеров
    private SpinnerNumberModel[] createSpinnerNumberModels(){
        return new SpinnerNumberModel[] {
                new SpinnerNumberModel(-5.0, -5.1, 4.9, 0.1),
                new SpinnerNumberModel(5.0, -4.9, 5.1, 0.1),
                new SpinnerNumberModel(-5.0, -5.1, 4.9, 0.1),
                new SpinnerNumberModel(5.0, -4.9, 5.1, 0.1)
        };
    }

    // создание спиннеров
    private JSpinner[] createJSpinners(SpinnerNumberModel[] models){
        return new JSpinner[]{
                new JSpinner(models[0]),
                new JSpinner(models[1]),
                new JSpinner(models[2]),
                new JSpinner(models[3]),
        };
    }

    // создание спиннеров с обработкой события
    private JSpinner[] createJSpinnersAndHandleEvents(){
        var models = createSpinnerNumberModels(); // создание моделей
        var spinners = createJSpinners(models); // создание спиннеров
        handleSpinnersEvent(spinners, models); // обработка события на спиннерах
        return spinners;
    }

    //endregion

    // создание лейблов
    private JLabel[] createLabels(){
        return new JLabel[] {
                new JLabel("Xmin: "),
                new JLabel("Xmax: "),
                new JLabel("Ymin: "),
                new JLabel("Ymax: ")
        };
    }

    // вызов обработки события у нужных слушателей
    private void executeResizedListeners(){
        listeners.forEach(l -> { // обращаемся к списку слушаталей
            if(l instanceof IResizedListener){ // проверяем является ли тип слушателя l необходимым типом IResizedListener
                // ведь только у типа IResizedListener существует необходимый метод resize, нужным образом обрабатывающим событие
                ((IResizedListener) l).resize();
            }
        });
    }

    // обработка события изменения значения спиннера у каждого спиннера
    private void handleSpinnersEvent(JSpinner[] spinners, SpinnerNumberModel[] models){
        spinners[0].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                models[0].setMinimum((double) spinners[0].getValue() - 1.);
                models[1].setMinimum((double) spinners[0].getValue() + 0.1);
                executeResizedListeners(); // вызов обработки события у нужных слушателей
            }
        });
        spinners[1].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                models[0].setMaximum((double) spinners[1].getValue() - 0.1);
                models[1].setMaximum((double) spinners[1].getValue() + 1.);
                executeResizedListeners(); // вызов обработки события у нужных слушателей
            }
        });
        spinners[2].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                models[2].setMinimum((double) spinners[2].getValue() - 1.);
                models[3].setMinimum((double) spinners[2].getValue() + 0.1);
                executeResizedListeners(); // вызов обработки события у нужных слушателей
            }
        });
        spinners[3].addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                models[2].setMaximum((double) spinners[3].getValue() - 0.1);
                models[3].setMaximum((double) spinners[3].getValue() + 1.);
                executeResizedListeners(); // вызов обработки события у нужных слушателей
            }
        });
    }

    // конфигурирование и установка layout'а
    private void configureAndSetLayout(JSpinner[] spinners, JLabel[] labels){
        GroupLayout gl = new GroupLayout(this);
        Dimension MIN_COMPONENT_SIZE = new Dimension(40, 20);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addComponent(labels[0], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(labels[2], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addComponent(spinners[0], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinners[2], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(20)
                .addGroup(gl.createParallelGroup()
                        .addComponent(labels[1], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(labels[3], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addComponent(spinners[1], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinners[3], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addGap(5)
                        .addComponent(labels[0], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinners[0], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(20)
                        .addComponent(labels[1], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinners[1], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addGap(5)
                        .addComponent(labels[2], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinners[2], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(20)
                        .addComponent(labels[3], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinners[3], MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
        );
        setLayout(gl);
    }
}

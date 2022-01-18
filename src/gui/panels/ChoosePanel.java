package gui.panels;

import gui.panels.listeners.IBaseListener;
import gui.panels.listeners.IChooseListener;
import gui.panels.listeners.IResizedListener;

import javax.swing.*;
import java.util.ArrayList;

public class ChoosePanel extends JPanel {
    private ArrayList<IBaseListener> listeners = new ArrayList<>();

    public ChoosePanel(){
        var checkExplicit = new JCheckBox("Explicit plot");
        var checkParametric = new JCheckBox("Parametric plot");
        configureAndSetLayout(checkExplicit, checkParametric);
    }
    private void configureAndSetLayout(JCheckBox checkExplicit, JCheckBox checkParametric){
        var gl = new GroupLayout(this);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addComponent(checkExplicit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(checkParametric, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addComponent(checkExplicit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(checkParametric, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(5)
        );
        setLayout(gl);
    }

    public void addChooseListener(IChooseListener l){
        listeners.add(l);
    }

    private void executeChooseListeners(JCheckBox checkExplicit, JCheckBox checkParametric){
        listeners.forEach(l -> { // обращаемся к списку слушаталей
            if(l instanceof IChooseListener){
                ((IChooseListener) l).choose(checkExplicit, checkParametric);
            }
        });
    }
}

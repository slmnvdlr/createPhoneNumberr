package gui.panels.listeners;

import gui.panels.ControlPanel;

/**
 * Интерфейс, характеризующий слушателя события изменения значений на спиннерах объекта класса {@link ControlPanel}
 * */
public interface IResizedListener extends IBaseListener{ // данный интерфейс является наследником интерфейса IBaseListener, сделано это для более гибкого манипулирования слушателями разных событий
    public void resize(); // обработка события
}

package ru.vladrus13.jgraphic.basic.components;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.KeyTaker;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.impl.ChooseEvent;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.jgraphic.factory.components.ButtonFactory;
import ru.vladrus13.jgraphic.factory.components.TextFactory;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Class with one or more buttons embodying choice
 *
 * @author vladrus13
 */
public class Choose extends Frame implements KeyTaker {
    /**
     * List of buttons
     */
    public final ArrayList<Button> buttons;
    /**
     * Number of current button. If button doesn't exist is zero
     */
    public int current;

    /**
     * Standard constructor for class
     *
     * @param name   system name of frame
     * @param start  start position for choose
     * @param size   size of choose class
     * @param parent parent frame
     */
    public Choose(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
        this.buttons = new ArrayList<>();
        current = 0;
        recalculateChildes();
    }

    public Choose(String name, Frame parent) {
        super(name, parent);
        this.buttons = new ArrayList<>();
        current = 0;
    }

    /**
     * Get choose from all buttons
     *
     * @param name       name of choose
     * @param count      count of buttons
     * @param start      start position of choose
     * @param size       size of choose
     * @param parent     parent frame of choose
     * @param buttonSize button size of choose
     * @param buttons    buttons array
     * @return choose
     * @throws AppException if we have problems with size of buttons and choose
     */
    public static Choose getInstance(String name, int count, Point start, Size size, Frame parent,
                                     Size buttonSize, Button[] buttons) throws AppException {
        if (count != buttons.length) {
            throw new IllegalArgumentException("Size of array not equals with count of buttons");
        }
        Choose choose = new Choose(name, start, size, parent);
        if (choose.size.x < buttonSize.x && buttonSize.coordinatesType == CoordinatesType.REAL) {
            throw new AppException("Size of button can't be greater than choose size: x");
        }
        if (choose.size.y < buttonSize.y * count && buttonSize.coordinatesType == CoordinatesType.REAL) {
            throw new AppException("Size of buttons can't be greater than choose size: y");
        }
        if (buttonSize.x > 1000 && buttonSize.coordinatesType == CoordinatesType.RATIO) {
            throw new AppException("Size of button on ratio can't be greater than choose size: x");
        }
        if (buttonSize.y * count > 1000 && buttonSize.coordinatesType == CoordinatesType.RATIO) {
            throw new AppException("Size of buttons on ratio can't be greater than choose size: y");
        }
        long underX;
        long underY;
        if (buttonSize.coordinatesType == CoordinatesType.RATIO) {
            underX = (1000 - buttonSize.x) / 2;
            underY = (1000 - count * buttonSize.y) / (count + 1);
        } else {
            underX = (choose.size.x - buttonSize.x) / 2;
            underY = (choose.size.y - buttonSize.y * count) / (count + 1);
        }
        for (int i = 0; i < count; i++) {
            Point point = new Point(underX, underY * (i + 1) + buttonSize.y * i, buttonSize.coordinatesType);
            buttons[i].setFrame(point, buttonSize.copy());
            choose.addButton(buttons[i]);
            buttons[i].setParent(choose);
        }
        choose.recalculate();
        return choose;
    }

    public static Choose getInstance(String name, int count, Point start, Size size, Frame parent,
                                     Size buttonSize, String[] texts, Event[] keyEvents, Event[] mouseEvents,
                                     ButtonFactory buttonFactory, TextFactory textFactory) throws AppException {
        if (count != texts.length) {
            throw new IllegalArgumentException("Size of array not equals with count of buttons");
        }
        Button[] buttons = new Button[count];
        Size fullSize = new Size(1000, 1000, CoordinatesType.RATIO);
        Point fullStart = new Point(0, 0, CoordinatesType.RATIO);
        for (int i = 0; i < count; i++) {
            Text text = textFactory.getInstance("text", texts[i], null);
            ClassicButton button = buttonFactory.getInstance("button" + i, text, null);
            button.setEventKey(keyEvents[i]);
            button.setEventMouse(mouseEvents[i]);
            text.setParent(button);
            text.setFrame(fullStart, fullSize);
            buttons[i] = button;
        }
        return getInstance(name, count, start, size, parent, buttonSize, buttons);
    }

    @Override
    public void recalculateChildes() {
        if (buttons != null) {
            for (Button button : buttons) {
                button.recalculate();
            }
        }
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        for (Button button : buttons) {
            button.draw(graphics);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_DOWN:
                buttons.get(current).setChoose(false);
                current = (current + 1) % buttons.size();
                buttons.get(current).setChoose(true);
                break;
            case KeyEvent.VK_UP:
                buttons.get(current).setChoose(false);
                current = (current + buttons.size() - 1) % buttons.size();
                buttons.get(current).setChoose(true);
                break;
            case KeyEvent.VK_ENTER:
                if (buttons.size() == 0) return;
                buttons.get(current).keyPressed(e);
        }
    }

    @Override
    public void callEvent(Event event) {
        if (event instanceof ChooseEvent) {
            switch (((ChooseEvent) event).event) {
                case ChooseEvent.END_CHOOSE:
                    try {
                        parent.removeFocused(this);
                        parent.removeChild(this);
                    } catch (AppException appException) {
                        appException.printStackTrace();
                    }
            }
            return;
        }
        parent.callEvent(event);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Add button to end of button list
     *
     * @param button button
     * @see Button
     */
    public void addButton(Button button) {
        if (buttons.isEmpty()) {
            button.setChoose(true);
        }
        buttons.add(button);
    }
}

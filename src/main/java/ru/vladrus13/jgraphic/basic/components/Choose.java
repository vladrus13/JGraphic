package ru.vladrus13.jgraphic.basic.components;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.KeyTaker;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.basic.event.returned.IntEvent;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;

import java.awt.*;
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
    private final ArrayList<Button> buttons;
    /**
     * Number of current button. If button doesn't exist is zero
     */
    private int current;

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
    public ru.vladrus13.jgraphic.basic.event.Event keyPressed(KeyEvent e) {
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
                return buttons.get(current).keyPressed(e);
        }
        return new IntEvent(IntEvent.NOTHING);
    }

    @Override
    public ru.vladrus13.jgraphic.basic.event.Event mousePressed(MouseEvent e) {
        return new IntEvent(IntEvent.NOTHING);
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

    /**
     * Get choose from all buttons
     *
     * @param name                 name of choose
     * @param count                count of buttons
     * @param start                start position of choose
     * @param size                 size of choose
     * @param parent               parent frame of choose
     * @param buttonSize           button size of choose
     * @param backgroundsChoose    backgrounds of chooses when it's active
     * @param backgroundsNotChoose backgrounds of chooses when it's not active
     * @param texts                texts of buttons
     * @param nameFont             name of font on buttons
     * @param fontSize             font size on buttons
     * @param colorFont            color font on buttons
     * @param textAlign            text align on buttons
     * @param eventsKeyboard       events if pressed buttons by keyboard
     * @param eventsMouse          events if pressed buttons by mouse
     * @return choose
     * @throws GameException if we have problems with size of buttons and choose
     */
    public static Choose getInstance(String name, int count, Point start, Size size, Frame parent,
                                     Size buttonSize, Filler[] backgroundsChoose,
                                     Filler[] backgroundsNotChoose, String[] texts,
                                     String nameFont, Size fontSize, Color colorFont, Text.TextAlign textAlign,
                                     ru.vladrus13.jgraphic.basic.event.Event[] eventsKeyboard,
                                     Event[] eventsMouse) throws GameException {
        if (count != texts.length || count != eventsKeyboard.length || count != eventsMouse.length ||
                count != backgroundsNotChoose.length || count != backgroundsChoose.length) {
            throw new IllegalArgumentException("Size of arrays not equals with count of buttons");
        }

        Choose choose = new Choose(name, start, size, parent);
        if (choose.size.x < buttonSize.x && buttonSize.coordinatesType == CoordinatesType.REAL) {
            throw new GameException("Size of button can't be greater than choose size: x");
        }
        if (choose.size.y < buttonSize.y * count && buttonSize.coordinatesType == CoordinatesType.REAL) {
            throw new GameException("Size of buttons can't be greater than choose size: y");
        }
        if (buttonSize.x > 1000 && buttonSize.coordinatesType == CoordinatesType.RATIO) {
            throw new GameException("Size of button on ratio can't be greater than choose size: x");
        }
        if (buttonSize.y * count > 1000 && buttonSize.coordinatesType == CoordinatesType.RATIO) {
            throw new GameException("Size of buttons on ratio can't be greater than choose size: y");
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
            int finalI = i;
            Point point = new Point(underX, underY * (i + 1) + buttonSize.y * i, buttonSize.coordinatesType);
            Button button = new Button("button" + i,
                    point.copy(), buttonSize.copy(), choose) {
                @Override
                public ru.vladrus13.jgraphic.basic.event.Event mousePressed(MouseEvent e) {
                    return eventsMouse[finalI];
                }

                @Override
                public ru.vladrus13.jgraphic.basic.event.Event keyPressed(KeyEvent e) {
                    return eventsKeyboard[finalI];
                }
            };
            Background background = new Background("background", new Point(0, 0, CoordinatesType.RATIO),
                    new Size(1000, 1000, CoordinatesType.RATIO), backgroundsChoose[i], button);
            Background backgroundNonChoose = new Background("backgroundNonChoose", new Point(0, 0, CoordinatesType.RATIO),
                    new Size(1000, 1000, CoordinatesType.RATIO), backgroundsNotChoose[i], button);
            Text text = new Text("text", new Point(0, 0, CoordinatesType.RATIO),
                    new Size(1000, 1000, CoordinatesType.RATIO), texts[i], nameFont, fontSize.copy(), colorFont, textAlign, button);
            button.setBackground(background);
            button.setBackgroundChoose(backgroundNonChoose);
            button.setText(text);
            choose.addButton(button);
        }
        return choose;
    }
}

package ru.vladrus13;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Background;
import ru.vladrus13.jgraphic.basic.components.Choose;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.basic.components.Text;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.factory.components.ButtonFactory;
import ru.vladrus13.jgraphic.factory.components.TextFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author vladrus13 on 23.03.2021
 **/
public class TestFrame extends Frame {

    private final Choose choose;

    /**
     * Standard constructor for Frame
     *
     * @param name   system name of frame
     * @param start  start position for frame
     * @param size   frame size
     * @param parent parent frame
     */
    public TestFrame(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
        Choose choose1;
        Size fullSize = new Size(1000, 1000, CoordinatesType.RATIO);
        Point fullStart = new Point(0, 0, CoordinatesType.RATIO);
        ButtonFactory buttonFactory = new ButtonFactory()
                .setBackground(new Background("back", fullStart.copy(), fullSize.copy(), new Filler(Color.BLUE), null))
                .setChooseBackground(new Background("chBack", fullStart.copy(), fullSize.copy(), new Filler(Color.RED), null));
        TextFactory textFactory = new TextFactory()
                .setNameFont("Inventory")
                .setColor(Color.BLACK)
                .setFontSize(new Size(300, 0, CoordinatesType.RATIO))
                .setTextAlign(Text.TextAlign.CENTER);
        try {
            choose1 = Choose.getInstance("choose", 3, new Point(100, 100, CoordinatesType.RATIO),
                    new Size(800, 800, CoordinatesType.RATIO), this, new Size(800, 100, CoordinatesType.RATIO),
                    new String[]{"1", "23", "456"}, buttonFactory, textFactory);
            choose1.recalculate();
        } catch (GameException e) {
            e.printStackTrace();
            choose1 = null;
        }
        choose = choose1;
        recalculateChildes();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        if (choose != null) {
            choose.draw(graphics);
        }
    }

    @Override
    public void recalculateChildes() {
        if (choose != null) {
            choose.recalculate();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
}

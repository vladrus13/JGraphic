package ru.vladrus13;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.components.Background;
import ru.vladrus13.jgraphic.basic.components.Filler;
import ru.vladrus13.jgraphic.basic.components.Table;
import ru.vladrus13.jgraphic.basic.components.Text;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.jgraphic.factory.components.ButtonFactory;
import ru.vladrus13.jgraphic.factory.components.TextFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author vladrus13 on 23.03.2021
 **/
public class TestFrame extends Frame {

    private final Table table;

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
        table = new Table("Table", new Point(0, 0, CoordinatesType.REAL), new Size(1000, 1000, CoordinatesType.RATIO), this);
        Frame[][] frames = new Frame[3][3];
        try {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    frames[i][j] = textFactory.getInstance(i + "_" + j, i + "_" + j, table);
                    frames[i][j].setFrame(new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO));
                }
            }
        } catch (AppException e) {
            e.printStackTrace();
        }
        table.setTable(frames);
        recalculateChildes();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        if (table != null) {
            table.draw(graphics);
        }
    }

    @Override
    public void recalculateChildes() {
        if (table != null) {
            table.recalculate();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
}

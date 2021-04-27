package ru.vladrus13.jgraphic.basic.components;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;

import java.util.Arrays;

public class Table extends UpdatedFrame {

    protected EmptyFrame[][] table;

    public Table(String name, Point start, Size size, Frame parent) {
        super(name, start, size, parent);
    }

    public Table(String name, Frame parent) {
        super(name, parent);
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        if (table != null) {
            for (EmptyFrame[] it : table) {
                for (EmptyFrame jt : it) {
                    if (jt != null) {
                        jt.update(delay);
                    }
                }
            }
        }
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        if (table != null) {
            for (EmptyFrame[] it : table) {
                for (EmptyFrame jt : it) {
                    if (jt != null) {
                        jt.draw(graphics);
                    }
                }
            }
        }
    }

    public void setTable(Frame[][] frames) {
        if (frames.length == 0) return;
        setTable(frames, 0, 0);
    }

    public void setTable(Frame[][] frames, int width, int height) {
        width = Math.max(frames.length, width);
        height = Math.max(Arrays.stream(frames).mapToInt(a -> a.length).max().orElseThrow(), height);
        Size onePieceSize = null;
        if (size != null) {
            onePieceSize = new Size(1000 / width, 1000 / height, CoordinatesType.RATIO);
        }
        table = new EmptyFrame[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (frames[i][j] == null) {
                    continue;
                }
                if (onePieceSize == null) {
                    table[i][j] = new EmptyFrame("table_" + i + "_" + j, this);
                } else {
                    table[i][j] = new EmptyFrame("table_" + i + "_" + j, new Point(onePieceSize.multiply(i, j)), onePieceSize.copy(), this);
                }
                table[i][j].addChild(frames[i][j]);
                frames[i][j].setParent(table[i][j]);
            }
        }
        recalculateChildes();
    }

    @Override
    public void recalculateChildes() {
        if (table == null) {
            return;
        }
        for (EmptyFrame[] it : table) {
            for (EmptyFrame jt : it) {
                if (jt != null) {
                    jt.recalculate();
                }
            }
        }
    }
}

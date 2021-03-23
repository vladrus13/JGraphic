package ru.vladrus13;

import ru.vladrus13.graphic.PCGraphicsAWTImpl;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.services.AppService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author vladrus13 on 23.03.2021
 **/
public class TestApp extends JPanel implements ActionListener, MouseListener, KeyListener {

    private final JFrame frame;
    private Frame current;

    public TestApp() {
        System.out.println("Run class: Test");
        AppService.setApp(this);
        frame = new JFrame("Test");
        int width = 800;
        int height = 800;
        frame.setSize(width, height);
        frame.setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            current = new TestFrame("Frame", new Point(0,0, CoordinatesType.REAL), new Size(800, 800, CoordinatesType.REAL), null);
        } catch (GameException e) {
            e.printStackTrace();
            return;
        }
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Insets insets = frame.getInsets();
                current.recalculate(
                        frame.getWidth() - insets.left - insets.right,
                        frame.getHeight() - insets.top - insets.bottom);
            }
        });
        frame.setVisible(true);
        frame.add(this);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        painter();
    }

    private void painter() {
        // TODO make FPS better mode
        long targetTime = 1000 / 60;
        long previousTime = System.currentTimeMillis() - targetTime;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - previousTime < targetTime) {
                try {
                    Thread.sleep(previousTime + targetTime - currentTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            previousTime = System.currentTimeMillis();
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        current.draw(new PCGraphicsAWTImpl(g));
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

package ru.vladrus13.jgraphic;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.services.AppService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author vladrus13 on 23.03.2021
 **/
public abstract class App extends JPanel implements ActionListener, MouseListener, KeyListener {
    protected final JFrame frame;
    protected Frame current;
    protected boolean isInterrupted;

    public App(int width, int height) {
        System.out.println("Run class: Test");
        AppService.setApp(this);
        frame = new JFrame("Test");
        frame.setSize(width, height);
        frame.setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Insets insets = frame.getInsets();
                if (current != null) {
                    current.recalculate(
                            frame.getWidth() - insets.left - insets.right,
                            frame.getHeight() - insets.top - insets.bottom);
                }
            }
        });
        frame.setVisible(true);
        frame.add(this);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
    }

    protected void painter() {
        // TODO make FPS better mode
        long targetTime = 1000 / 60;
        long previousTime = System.currentTimeMillis() - targetTime;
        while (!isInterrupted) {
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
}

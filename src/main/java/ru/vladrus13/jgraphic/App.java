package ru.vladrus13.jgraphic;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.property.MainProperty;
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
        AppService.setApp(this);
        MainProperty.read();
        frame = new JFrame(this.getClass().getSimpleName());
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
        long targetTime = 1000 / MainProperty.getInteger("window.maxFPS");
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
            if (current instanceof UpdatedFrame) {
                ((UpdatedFrame) current).update(System.currentTimeMillis() - previousTime);
            }
            previousTime = System.currentTimeMillis();
            repaint();
        }
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space_chimpanzees;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author christian
 */
public class Controller
        extends JComponent implements ActionListener, KeyListener
{
    Timer timer;
    private JFrame mainwindow;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private Monkey monkey;
    ArrayList<Banana> bananas = new ArrayList<Banana>();
    ArrayList<Plumber> plumbers = new ArrayList<Plumber>();

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, width, height);
        monkey.moveSelf();
        monkey.paintSelf(g2);
    }

    public void getGoing()
    {
        mainwindow = new JFrame("SPACE CHIMPANZEES!");
        mainwindow.setVisible(true);
        mainwindow.setSize(width, height);
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainwindow.add(this);
        mainwindow.addKeyListener(this);
        timer = new Timer(20, this);
        timer.start();
        monkey = new Monkey();
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource().equals(timer))
        {
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT)//left
        {
            monkey.setMonkeyDirection(monkey.getMonkeyDirection() + 10);
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT)//right
        {
            monkey.setMonkeyDirection(monkey.getMonkeyDirection() - 10);
        } else if (ke.getKeyCode() == KeyEvent.VK_UP)//up
        {
            monkey.setMonkeySpeed(monkey.getMonkeySpeed() - 1);
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN)//down
        {
            monkey.setMonkeySpeed(monkey.getMonkeySpeed() + 1);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

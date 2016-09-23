package Tetris;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MESSAGE;
import interfaces.OBSERVED;
import interfaces.OBSERVER;
import view.GAME_PANEL;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.Image;
import javax.swing.ImageIcon;

public class VIEW extends JFrame implements OBSERVED
{
    private OBSERVER observer[];
    private static VIEW instance;
    private MODEL m;

    private GAME_PANEL game;
    private JPanel stats;
    private JLabel view_score;
    private JLabel view_rows;
    private JLabel view_level;
    private Image icon;

    private boolean gameOver;

    private VIEW()
    {
        super("Tetris");
        observer = new OBSERVER[0];
        m = MODEL.GetInstance();
        game = new GAME_PANEL();
        game.setPreferredSize(new Dimension(300, 600));
        game.setMaximumSize(new Dimension(300, 600));

        view_score = new JLabel();
        view_score.setSize(300, 50);
        view_score.setFont(new Font("Courier", Font.BOLD, 20));
        view_rows = new JLabel();
        view_rows.setSize(300, 50);
        view_rows.setFont(new Font("Courier", Font.BOLD, 20));
        view_level = new JLabel();
        view_level.setSize(300, 50);
        view_level.setFont(new Font("Courier", Font.BOLD, 20));

        stats = new JPanel();
        stats.setPreferredSize(new Dimension(300, 600));
        stats.setMaximumSize(new Dimension(300, 600));
        stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
        stats.setSize(300, 600);
        stats.add(view_score);
        stats.add(view_rows);
        stats.add(view_level);

        this.addKeyListener(new KeyListener() {
                @Override
                public synchronized void keyPressed(KeyEvent e)
                {
                    if(e.getKeyCode() == KeyEvent.VK_UP)
                        Notify(new MESSAGE(4));
                    else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                        Notify(new MESSAGE(5));
                    else if(e.getKeyCode() == KeyEvent.VK_LEFT)
                        Notify(new MESSAGE(3));
                    else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                        Notify(new MESSAGE(2));
                    else if(e.getKeyCode() == KeyEvent.VK_P)
                        Notify(new MESSAGE(7));
                    else if(e.getKeyCode() == KeyEvent.VK_N)
                        Notify(new MESSAGE(8));
                }

                @Override
                public synchronized void keyReleased(KeyEvent e)
                {
                    if(e.getKeyCode() == KeyEvent.VK_DOWN)
                        Notify(new MESSAGE(6));
                }

                @Override
                public synchronized void keyTyped(KeyEvent e)
                {
                }
            });

        gameOver = false;
        icon = new ImageIcon("icon.gif").getImage();
        this.setLayout(new GridLayout(1, 2));
        this.add(game);
        this.add(stats);
        this.setResizable(false);
        this.setIconImage(icon);
        this.setLocation(100, 100);
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Update();
    }

    public static VIEW GetInstance()
    {
        if(VIEW.instance == null)
        {
            VIEW.instance = new VIEW();
        }
        return VIEW.instance;
    }

    private void Notify(MESSAGE m)
    {
        for(int i = 0; i < observer.length; i++)
        {
            observer[i].GetMessage(m);
        }
    }

    @Override
    public void AddObserver(OBSERVER o)
    {
        OBSERVER temp[] = new OBSERVER[observer.length];
        for(int i = 0; i < observer.length; i++)
        {
            temp[i] = observer[i];
        }
        observer = new OBSERVER[observer.length + 1];
        for(int i = 0; i < observer.length - 1; i++)
        {
            observer[i] = temp[i];
        }
        observer[observer.length - 1] = o;
    }

    @Override
    public void RemoveObserver(OBSERVER o)
    {
        for(int i = 0; i < observer.length; i++)
        {
            if(observer[i].equals(o))
            {
                OBSERVER temp[] = new OBSERVER[observer.length - 1];
                int j1 = 0;
                for(int j2 = 0; j2 < temp.length; j2++, j1++)
                {
                    if(j1 == i) { j1 += 1; }
                    temp[j2] = observer[j1];
                }
            }
        }
    }

    public boolean GameOver()
    {
        game.SetGameOver(true);
        
        if(JOptionPane.showConfirmDialog(this, "Game Over! \nScore: " + m.GetScore(), "Game Over", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            game.SetGameOver(false);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void Update()
    {
        view_score.setText(" Score: " + String.valueOf(m.GetScore()));
        view_rows.setText(" Rows : " + String.valueOf(m.GetRows()));
        view_level.setText(" Level: " + String.valueOf(m.GetLevel()));
        repaint();
    }
}

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Rectangle;

public class GAME_PANEL extends JPanel
{
    // Attribute
    private MODEL m;
    private boolean gameOver;

    // Konstruktor
    public GAME_PANEL()
    {
        super();
        m = MODEL.GetInstance();
        gameOver = false;
        this.setSize(300, 600);
    }

    public void SetGameOver(boolean b)
    {
        gameOver = b;
    }
    
    // Methoden
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        
        for(int i = 0; i < 600; i += 30)
        {
            for(int j = 0; j < 300; j += 30)
            {
                g2.setPaint(m.GetAWTColor(j / 30, i / 30));
                g2.fill(new Rectangle(j, i, 30, 30));
            }
        }
        for(int i = 0; i < 600; i += 30)
        {
            for(int j = 0; j < 300; j += 30)
            {
                g2.setPaint(Color.BLACK);
                g2.draw(new Rectangle(j, i, 30, 30));
            }
        }
        
        if(gameOver)
        {
            g2.setPaint(new Color(0, 0, 0, 127));
            g2.fill(new Rectangle(0, 0, 300, 600));
        }
    }
}

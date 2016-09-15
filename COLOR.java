/*
 * 0: weiß
 * 1: schwarz
 * 2: türkis
 * 3: blau
 * 4: orange
 * 5: gelb
 * 6: grün
 * 7: violett
 * 8: rot
 * 9: grau
 */

public class COLOR
{
    private static final java.awt.Color col[] = new java.awt.Color[9]; // = { WHITE, ...} 
    //@@@
    private int number;
    private java.awt.Color color;
    
    
    public COLOR()
    {
        //@@@
        //@@ private!!!!
    }
    
    public COLOR(COLOR f)
    {
        this.number = f.number;
        this.color = f.color;
    }
    
    public COLOR(int i)
    {
        this.number = i;
        this.color = NumberToColor(i);
    }
    
    public static java.awt.Color NumberToColor(int nr)
    {
        if(nr == 0)
        {
            return java.awt.Color.WHITE;
        }
        else if(nr == 1)
        {
            return java.awt.Color.BLACK;
        }
        else if(nr == 2)
        {
            return java.awt.Color.CYAN;
        }
        else if(nr == 3)
        {
            return java.awt.Color.BLUE;
        }
        else if(nr == 4)
        {
            return java.awt.Color.ORANGE;
        }
        else if(nr == 5)
        {
            return java.awt.Color.YELLOW;
        }
        else if(nr == 6)
        {
            return java.awt.Color.GREEN;
        }
        else if(nr == 7)
        {
            return java.awt.Color.MAGENTA;
        }
        else if(nr == 8)
        {
            return java.awt.Color.RED;
        }
        else if(nr == 9)
        {
            return java.awt.Color.GRAY;
        }
        else
        {
            return null;
        }
        
    }
    
    public int GetNumber()
    {
        return this.number;
    }
    
    public java.awt.Color GetAWTColor()
    {
        return this.color;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        return this.number == ((COLOR) obj).number;
    }
}

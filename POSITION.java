/*
 * @Author: Daniel Milz
 */

public class POSITION
{
    private int xKoordinate, yKoordinate;

    public POSITION(int x, int y)
    {
        this.xKoordinate = x;
        this.yKoordinate = y;
    }

    public void setX(int x) { this.xKoordinate = x; }
    public void setY(int y) { this.yKoordinate = y; }
    public int getX() { return this.xKoordinate; }
    public int getY() { return this.yKoordinate; }
    
    public void setRandomX() { this.xKoordinate = RANDOM.GetInt(10); }
}

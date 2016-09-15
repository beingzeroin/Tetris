

import java.util.LinkedList;

public class TETROMINO
{
    protected POSITION hauptstein;
    protected POSITION[] steine; //relative Koordinaten zu hauptstein
    protected COLOR farbe;
    protected int drehung;
    protected boolean enabled;
    protected boolean feltDown;

    protected TETROMINO()
    {
        this(false);
        this.SetSteine();
    }

    protected TETROMINO(boolean container) //for container
    {
        this.farbe = new COLOR();
        this.drehung = 0;
        this.enabled = true;
        this.hauptstein = new POSITION(RANDOM.GetInt(2,8),0);
        this.steine = new POSITION[4];
        this.feltDown = false;
    }

    protected TETROMINO(POSITION pos, COLOR f)
    {
        this.hauptstein = new POSITION(pos.getX(), pos.getY());
        this.farbe = new COLOR(f);
        this.SetSteine();
    }

    public synchronized void Drehen(int grad) 
    {
        if(this.enabled == false) {return; }
        int d = (drehung+grad) % 360;
        if((d % 90) == 0)
        {
            drehung = d;
            setDrehung();
        }
    }

    protected synchronized void setDrehung() 
    {
        int temp;
        for(int i = 0; i < 4; i++ )
        {
            temp = this.steine[i].getX();
            this.steine[i].setX(-this.steine[i].getY());
            this.steine[i].setY(temp);
        }
    }

    public void SetSteine() {} //Override!

    public synchronized void Move(boolean right) 
    {
        if(!this.enabled) { return; }
        if(right)
        {
            this.hauptstein.setX(this.hauptstein.getX()+1);
        }
        else
        {
            this.hauptstein.setX(this.hauptstein.getX()-1);
        }
    }

    public synchronized boolean isMovingPossible(boolean right, CONTAINER con)
    {
        int richtung = 0;
        if(right == true) { richtung = 1; }
        else {richtung = -1; }

        if(this.enabled == false) { return false; }

        for(int i = 0; i < 4; i++)
        {
            if(((this.steine[i].getX() + this.hauptstein.getX() + richtung) == -1) ||  ((this.steine[i].getX() + this.hauptstein.getX() + richtung) == 10))
            {
                return false;
            }
        }
        for(int i = 0; i < 4; i++){
            if(con.isSet(this.hauptstein.getX() + this.steine[i].getX() + richtung, this.hauptstein.getY() + this.steine[i].getY(), this))
            {
                return false;
            }
        }
        return true;
    }

    public synchronized void Clock() 
    {
        if(!this.enabled) {return;}
        this.hauptstein.setY(this.hauptstein.getY()+1);
    }

    public synchronized boolean isClockPossible(CONTAINER c, MODEL m)
    {
        if(this.enabled == false) { return false; }

        //Spielfeld zu ende
        for(int i = 0; i < 4; i++)
        {
            if(this.steine[i].getY() + this.hauptstein.getY() == 19)
            {
                this.enabled = false;
            }
        }

        //Spielstein im Weg
        for(int i = 0; i < 4; i++){
            if(c.isSet(this.hauptstein.getX() + this.steine[i].getX(), this.hauptstein.getY() + 1 + this.steine[i].getY(), this))
            {
                this.enabled = false;
            }
        }

        //Spielfeld nach obenhin voll
        if(this.enabled == false) { 
            for(int i = 0; i < 4; i++)
            {
                if(this.steine[i].getY() + this.hauptstein.getY() < 0)
                {
                    this.enabled = false;
                    m.GetMessage(new MESSAGE(-1));
                }
            }
            return false; 
        }

        return true;
    }

    public void ColorUp(COLOR f) 
    {
        this.farbe = f;
    }

    public void SetEnable(boolean bool)
    {
        this.enabled = bool;
    }

    public void RemoveRow(int nr)
    {
        for(int i = 0; i < 4; i++)
        {
            if(this.steine[i] == null) { continue; } //Überspringen
            if((this.hauptstein.getY() + this.steine[i].getY()) == nr)
            {
                this.steine[i] = null;
            }
        }
    }

    public boolean isSet(int x, int y, TETROMINO t)
    {
        if(t == this) {return false;}
        boolean yKoord, xKoord;
        for(int i = 0; i < 4; i++)
        {
            if(this.steine[i] == null) { continue; } //Überspringen
            yKoord = ((this.hauptstein.getY() + this.steine[i].getY()) == y);
            xKoord = ((this.hauptstein.getX() + this.steine[i].getX()) == x);
            if((yKoord==true) && (xKoord==true))
            { 
                return true; 
            }
        }
        return false;
    }

    public void ClearEmpty(){} //for Container

    public boolean[] IsRowComplete(int nr)
    {
        boolean tp[] = new boolean[10];
        for(int i = 0; i < 10; i++)
        {
            tp[i] = false; 
        }
        int temp = 0;
        for(int i = 0; i < 4; i++)
        {
            if(this.steine[i] == null) { continue; } //Überspringen
            int y = this.hauptstein.getY();
            int y2 = this.steine[i].getY();
            if((y + y2) == nr)
            {
                tp[this.hauptstein.getX() + this.steine[i].getX()] = true;
            }
        }
        return tp;
    }

    public boolean IsEmpty()
    {
        for(int i = 0; i < 4; i++)
        {
            if(steine[i] != null) { return false; }
        }
        return true;
    }

    public COLOR GetColor(int x, int y)
    {
        for(int i = 0; i < 4; i++)
        {
            if(steine[i] == null) { continue; } //Überspringen
            if(((hauptstein.getX() + steine[i].getX()) == x) && ((hauptstein.getY() + steine[i].getY()) == y)) 
            {
                return this.farbe;
            }
        }
        return null;
    }

    public boolean equals(Object obj)
    {
        TETROMINO t = (TETROMINO) obj; //Typecast
        if(t.hauptstein.getX() != this.hauptstein.getX()) { return false; }
        if(t.hauptstein.getY() != this.hauptstein.getY()) { return false; }
        for(int i = 0; i < 4; i++)
        {
            if(t.steine[i].getX() != this.steine[i].getX()) { return false; }
            if(t.steine[i].getX() != this.steine[i].getX()) { return false; }
        }
        return true;
    }

    public void Add(TETROMINO t){} //Tue Nichts

    public void FallDown(int row)
    {
        for(int i = 0; i < 4; i++)
        {
            if(this.steine[i] == null) { continue; } //Überspringen
            if((this.hauptstein.getY() + this.steine[i].getY()) < row)
            {
                this.hauptstein.setY(this.hauptstein.getY() + 1);
                this.feltDown = true;
                return;
            }
        }
    }

}

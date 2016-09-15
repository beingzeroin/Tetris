import java.util.LinkedList;
import java.util.Iterator;

public class CONTAINER extends TETROMINO
{
    private LinkedList<TETROMINO> list;
    
    public CONTAINER()
    {
        super(true);
        super.farbe = null;
        super.hauptstein = null;
        this.list = new LinkedList<TETROMINO>();
        this.SetSteine();
    }

    @Override
    public void SetSteine() 
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        while(it.hasNext())
        {
            it.next().SetSteine();
        }
    }
    
    @Override
    public void Drehen(int grad) 
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        while(it.hasNext())
        {
            it.next().Drehen(grad);
        }
    }
    
    @Override
    public void Move(boolean right) 
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        while(it.hasNext())
        {
            it.next().Move(right);
        }
    }
    
    @Override
    public boolean isMovingPossible(boolean right, CONTAINER c) 
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        boolean temp = false;
        while(it.hasNext())
        {
            temp |= it.next().isMovingPossible(right, this);
        }
        return temp;
    }
    
    @Override
    public void Clock()
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        while(it.hasNext())
        {
            it.next().Clock();
        }
    }
    
    @Override
    public boolean isClockPossible(CONTAINER c, MODEL m) 
    { 
        Iterator <TETROMINO> it = this.list.listIterator();
        boolean temp = false;
        while(it.hasNext())
        {
            temp |= it.next().isClockPossible(this, m);
        }
        return temp;
    }
    
    @Override
    public void ColorUp(COLOR f)
    { 
        Iterator <TETROMINO> it = this.list.listIterator();
        while(it.hasNext())
        {
            it.next().ColorUp(f);
        }
    }
    
    @Override
    public void SetEnable(boolean bool)
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        while(it.hasNext())
        {
            it.next().SetEnable(bool);
        }
    }
    
    @Override
    public void ClearEmpty()
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        while(it.hasNext())
        {
            it.next().ClearEmpty();
        }
    }
    
    @Override
    public boolean[] IsRowComplete(int nr)
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        boolean temp[] = new boolean[10];
        boolean tp[] = new boolean[10];
        while(it.hasNext())
        {
            tp = it.next().IsRowComplete(nr);
            for(int i = 0; i < 10; i++)
            {
                temp[i] |= tp[i];
            }
        }
        return temp;
    }
    
    @Override
    public void RemoveRow(int nr)
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        while(it.hasNext())
        {
            it.next().RemoveRow(nr);
        }
    }
    
    @Override
    public COLOR GetColor(int x, int y)
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        COLOR c;
        while(it.hasNext())
        {
            c = it.next().GetColor(x,y);
            if(c != null)
            {
                return c;
            }
        }
        return new COLOR(9); //grau
    }
    
    /*
    @Override
    public boolean equals(Object obj)
    {
        return lTetromino.equals( ((CONTAINER) obj).lTetromino);
    }
    */
    
    @Override
    public void Add(TETROMINO t)
    {
        this.list.add(t);
    }
    
    @Override
    public void FallDown(int i)
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        while(it.hasNext())
        {
            it.next().FallDown(i);
        }
    }
    
    @Override
    public boolean isSet(int x, int y, TETROMINO t)
    {
        Iterator <TETROMINO> it = this.list.listIterator();
        boolean temp = false;
        while(it.hasNext())
        {
            temp |= it.next().isSet(x,y,t);
        }
        return temp;
    }
}

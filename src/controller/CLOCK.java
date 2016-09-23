package controller;
import javax.swing.Timer;

import interfaces.OBSERVED;
import interfaces.OBSERVER;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CLOCK implements OBSERVED, ActionListener
{
    private static CLOCK instance;
    private OBSERVER observer[];
    private Timer timer;
    
    private CLOCK()
    {
        observer = new OBSERVER[0];
        timer = null;
    }
    
    public static CLOCK GetInstance()
    {
        if(CLOCK.instance == null)
        {
            CLOCK.instance = new CLOCK();
        }
        return CLOCK.instance;
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

   public void Starten (int takt)
    {
        if (timer == null)
        {
            timer = new Timer (takt, this);
            timer.start ();
        }
    }
    
    public void Pausieren ()
    {
        if (timer.isRunning ())
        {
            timer.stop ();
        }
    }

    public void Fortsetzen ()
    {
        if (!timer.isRunning ())
        {
            timer.start ();
        }
    }

    public void Stoppen ()
    {
        if (timer != null)
        {
            timer.stop ();
            timer = null;
        }
    }
    
    @Override
    public void actionPerformed (ActionEvent e)
    {
        this.Notify(MESSAGE.CODE_1);
    }
}



public class CONTROLLER implements OBSERVER
{
    private static CONTROLLER instance;
    
    private MODEL model;
    private VIEW view;
    
    private CLOCK clock;
    
    private int clockTime;
    private boolean pause;
    private boolean keypressed;
    
    private CONTROLLER()
    {
        this.clockTime = 5000/6;
    }
    
    public static CONTROLLER GetInstance()
    {
        if(CONTROLLER.instance == null)
        {
            CONTROLLER.instance = new CONTROLLER();
        }
        return CONTROLLER.instance;
    }
    
    @Override
    public synchronized void GetMessage(MESSAGE m)
    {
        int code = m.GetCode();
        if(code == 0)
        {
            
        }
        else if(code == 1)
        {          
            view.Update();
            model.Clock();
        }
        else if(code == 5)
        {
            if(!this.keypressed)
            {
                this.keypressed = true;
                this.clock.Stoppen();
                this.clock.Starten(50);
            }
        }
        else if(code == 6)
        {
            if(this.keypressed)
            {
                this.keypressed = false;
                this.clock.Stoppen();
                this.clock.Starten(this.clockTime);
            }
        }
        else if(code == 7)
        {
            if(this.pause)
            {
                this.Continue();
                this.pause = false;
            }
            else
            {
                this.Pause();
                this.pause = true;
            }
        }
        else if(code == 9)
        {
            this.clockTime = 5000/(this.model.GetLevel() + 5);
            this.SetClockTime();
        }
        else if(code == -1)
        {
            this.GameOver();
        }
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if((CONTROLLER) obj == CONTROLLER.instance) { return true; }
        return false;
    }
    
    public void SetView(VIEW v)
    {
        this.view = v;
    }
    
    public void SetModel(MODEL m)
    {
        this.model = m;
    }
    
    public void SetClock(CLOCK c)
    {
        this.clock = c;
    }
    
    public void SetClockTime()
    {
        this.clock.Starten(clockTime);
    }
    
    public void Starten()
    {
        this.clock.Starten(clockTime);
        this.model.Start();
    }
    
    public void Pause()
    {
        this.clock.Pausieren();
    }
    
    public void Continue()
    {
        this.clock.Fortsetzen();
    }
    
    public void Stop()
    {
        this.clock.Fortsetzen();
    }
    
    private void GameOver()
    {
        boolean bool = this.view.GameOver();
        if(bool)
        {
            this.NewGame();
        }
        else
        {
            System.exit(0);
        }
        
    }
    
    private void NewGame()
    {
        this.Stop();
        this.model.NewGame();
    }
}

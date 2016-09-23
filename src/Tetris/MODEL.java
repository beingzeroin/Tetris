package Tetris;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import controller.MESSAGE;
import interfaces.OBSERVED;
import interfaces.OBSERVER;
import model.CONTAINER;
import model.RANDOM;
import model.TETROMINO;
import model.TETROMINOFACTORY;
import model.TETROMINO_TYPE;
import view.COLOR;

public class MODEL implements OBSERVED, OBSERVER
{
    private static MODEL instance;
    private OBSERVER observer[];
    private TETROMINO container;
    
    private int score;
    private int deleted_rows;
    private int level;
    
    private File audioFile;
    private Clip theme;
    
    private MODEL()
    {
        observer = new OBSERVER[0];
        this.Initalize();
        /*
        audioFile = new File("theme.wav");
        AudioInputStream audioInput;
        try
        {
            audioInput = AudioSystem.getAudioInputStream(audioFile);
            theme = AudioSystem.getClip();
            theme.open(audioInput);
            theme.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e)
        {
        }
        */
    }
    
    private synchronized void Notify(MESSAGE m) //Benachrichtigen
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
    
    @Override
    public synchronized void GetMessage(MESSAGE m)
    {
        if(m.GetCode() == 2)
        {
            this.Move(true);
        }
        else if(m.GetCode() == 3)
        {
            this.Move(false);
        }
        else if(m.GetCode() == 4)
        {
            this.Drehen();
        }
        else if(m.GetCode() == 8)
        {
            this.NewGame();
        }
        else if(m.GetCode() == -1)
        {
            this.Notify(new MESSAGE(-1));
        }
    }

    public static MODEL GetInstance()
    {
        if(MODEL.instance == null)
        {
            MODEL.instance = new MODEL();
        }
        return instance;
    }
    
    private void Initalize()
    {
        this.score = 0;
        this.deleted_rows = 0;
        this.level = 0;
        this.container = TETROMINOFACTORY.createTetromino(new TETROMINO_TYPE(7));
    }
    
    public void Start()
    {
        this.Initalize();
        this.NewStein();
    }
    
    public synchronized void Clock()
    {
        if(this.container.isClockPossible(null, this))
        {
            this.container.Clock();
            return;
        }
        container.SetEnable(false);
        score += 1;
        this.RemoveRow();
        this.NewStein();
    }
    
    public synchronized void Move(boolean right)
    {
        if(this.container.isMovingPossible(right, null))
        {
            this.container.Move(right);
        }
    }
    
    public synchronized void Drehen()
    {
        this.container.Drehen(90);
    }
    
    private void NewStein()
    {
        this.container.Add(TETROMINOFACTORY.createTetromino(new TETROMINO_TYPE(RANDOM.GetInt(6)))); //@@@
    }
    
    private void RemoveRow()
    {
        int tmp = 0;
        for(int i = 0; i < 20; i++) ///!!! depends on Height
        {
            if(this.IsRowComplete(i))
            {
                tmp += 1;
                this.container.RemoveRow(i);
                this.container.FallDown(i);
                this.score += 10*tmp;
                this.deleted_rows += 1;
                if(this.deleted_rows == 10)
                {
                    this.deleted_rows = 0;
                    this.level += 1;
                    this.Notify(new MESSAGE());
                }
            }
        }
        this.ClearEmpty();
    }
    
    private void ClearEmpty()
    {
        this.container.ClearEmpty();
    }
    
    private boolean IsRowComplete(int nr)
    {
        boolean in[] = new boolean[10];
        in = this.container.IsRowComplete(nr);
        for(int i = 0; i < 10; i++)
        {
            if(!in[i])
            {
                return false;
            }
        }
        return true;
    }

    
    //VIEW
    public int GetScore()
    {
        return this.score;
    }
    
    public int GetRows()
    {
        return this.deleted_rows;
    }
    
    public int GetLevel()
    {
        return this.level;
    }
    
    public COLOR GetColor(int x, int y)
    {
        return this.container.GetColor(x, y);
    }
    
    public java.awt.Color GetAWTColor(int x, int y)
    {
        return this.container.GetColor(x, y).GetAWTColor();
    }
    
    public void NewGame()
    {
        this.container = new CONTAINER();
        this.score = 0;
        this.deleted_rows = 0;
        this.level = 0;
    }
    
}

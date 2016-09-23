package controller;

/*
 * DESCRIPTION:
 * -1 : Game Over!
 * 0: Fertig / Bereit; Model
 * 1: Time elapsed; Clock
 * 2: Move right
 * 3: Move left
 * 4: Drehen
 * 5: Schneller: Beginn
 * 6: Schneller: Stop
 * 7: pause
 * 8: new game
 * 9: LevelUp
 */

public class MESSAGE
{
    private int code;
    
    public static final MESSAGE CODE_0 = new MESSAGE(0);
    public static final MESSAGE CODE_1 = new MESSAGE(1);
    public static final MESSAGE CODE_2 = new MESSAGE(2);
    public static final MESSAGE CODE_3 = new MESSAGE(3);
    public static final MESSAGE CODE_4 = new MESSAGE(4);
    public static final MESSAGE CODE_5 = new MESSAGE(5);
    
    public MESSAGE()
    {
        
    }
    
    public MESSAGE(int nr)
    {
        this.code = nr;
    }
    
    public void SetCode(int nr)
    {
        this.code = nr;
    }
    
    public int GetCode()
    {
        return this.code;
    }
}

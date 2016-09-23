package model;


import java.util.Random;

public class RANDOM
{
    private static Random r = new Random();
    private static RANDOM instance;
    
    private RANDOM() {}
    
    public static RANDOM getInstance()
    {
        if(instance == null)
        {
            RANDOM.instance = new RANDOM();
        }
        return RANDOM.instance;
    }
    
    public static int GetInt(int max)
    {
        return r.nextInt(max+1);
    }
    
    public static int GetInt(int min, int max)
    {
        return r.nextInt(max-min) + min;
    }
}













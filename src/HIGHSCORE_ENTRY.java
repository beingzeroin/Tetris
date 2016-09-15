import java.io.Serializable;

public class HIGHSCORE_ENTRY implements Comparable, Serializable
{
    // Attribute
    private int score;
    private String name;

    // Konstruktor
    public HIGHSCORE_ENTRY(int score, String name)
    {
        this.score = score;
        this.name = name;
    }

    // Methoden
    @Override
    public int compareTo(Object o)
    {
        HIGHSCORE_ENTRY e = (HIGHSCORE_ENTRY) o;
        if(this.score > e.score)
            return 1;
        else if(this.score < e.score)
            return -1;
        else
            return 0;
    }
    
    @Override
    public String toString()
    {
        return name + "\t\t\t\t\t" + score;
    }

}

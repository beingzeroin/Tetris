import java.io.Serializable;
import java.util.LinkedList;

public class HIGHSCORE_LIST implements Serializable
{
    // Attribute
    private static final int MAX_SIZE = 20;
    private LinkedList<HIGHSCORE_ENTRY> list;
    
    // Konstruktor
    public HIGHSCORE_LIST()
    {
        list = new LinkedList<>();
    }

    // Methoden
    public void AddScore(HIGHSCORE_ENTRY e)
    {
        list.add(e);
        java.util.Collections.sort(list);
        if(list.size() > MAX_SIZE)
            list.remove(MAX_SIZE);
    }
    
    @Override
    public String toString()
    {
        for(int i = 0; i < list.size(); i++)
        {
            System.out.print((i + 1) + ". ");
            System.out.print(list.get(i));
            System.out.println();
        }
        return null;
    }
}

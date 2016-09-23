package interfaces;
import controller.MESSAGE;

public interface OBSERVER
{
    public void GetMessage(MESSAGE m);
    public boolean equals(Object obj);
}

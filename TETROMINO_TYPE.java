/*
 * @Author: Daniel Milz
 */
public class TETROMINO_TYPE
{
    private int type;
    
    public TETROMINO_TYPE(int nr)
    {
        this.type = nr;
    }
    
    public void setType(int nr) { this.type = nr; }
    public int getType() { return this.type; }
    
    @Override
    public boolean equals(Object obj)
    {
        if(((TETROMINO_TYPE) obj).type == this.type)
        {
            return true;
        }
        return false;
    }
    
}


public class S_TETROMINO extends TETROMINO
{
    public S_TETROMINO()
    {
        super();
        this.ColorUp(new COLOR(6));
    }

    @Override
    public void SetSteine()
    {
        super.steine[0] = new POSITION(0,0);
        super.steine[1] = new POSITION(1,0);
        super.steine[2] = new POSITION(-1,-1);
        super.steine[3] = new POSITION(0,-1);
    }
}

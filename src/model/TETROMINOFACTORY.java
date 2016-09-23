package model;

import tetrominos.I_TETROMINO;
import tetrominos.J_TETROMINO;
import tetrominos.L_TETROMINO;
import tetrominos.O_TETROMINO;
import tetrominos.S_TETROMINO;
import tetrominos.T_TETROMINO;
import tetrominos.Z_TETROMINO;

public class TETROMINOFACTORY
{
    public static final TETROMINO_TYPE O_TYPE = new TETROMINO_TYPE( 0 );
    public static final TETROMINO_TYPE T_TYPE = new TETROMINO_TYPE( 1 );
    public static final TETROMINO_TYPE J_TYPE = new TETROMINO_TYPE( 2 );
    public static final TETROMINO_TYPE I_TYPE = new TETROMINO_TYPE( 3 );
    public static final TETROMINO_TYPE L_TYPE = new TETROMINO_TYPE( 4 );
    public static final TETROMINO_TYPE S_TYPE = new TETROMINO_TYPE( 5 );
    public static final TETROMINO_TYPE Z_TYPE = new TETROMINO_TYPE( 6 );
    public static final TETROMINO_TYPE CONTAINER_TYPE = new TETROMINO_TYPE( 7 );
    public static final int TYPES = 7;
    
    private static TETROMINOFACTORY instance;
    
    private TETROMINOFACTORY() {}
    
    public static TETROMINOFACTORY getInstance()
    {
        if(TETROMINOFACTORY.instance == null)
        {
            TETROMINOFACTORY.instance = new TETROMINOFACTORY();
        }
        return TETROMINOFACTORY.instance;
    }
    
    public static TETROMINO createTetromino(TETROMINO_TYPE type)
    {
        if( type.equals(O_TYPE))
        {
            return new O_TETROMINO();
        }
        else if( type.equals(T_TYPE))
        {
            return new T_TETROMINO();
        }
        else if( type.equals(J_TYPE))
        {
            return new J_TETROMINO();
        }
        else if( type.equals(I_TYPE))
        {
            return new I_TETROMINO();
        }
        else if( type.equals(L_TYPE))
        {
            return new L_TETROMINO();
        }
        else if( type.equals(S_TYPE))
        {
            return new S_TETROMINO();
        }
        else if( type.equals(Z_TYPE))
        {
            return new Z_TETROMINO();
        }
        else if( type.equals(CONTAINER_TYPE))
        {
            return new CONTAINER();
        }
        else
        {
            return null;
        }
    }
}

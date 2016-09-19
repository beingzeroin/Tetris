
public class TETRIS
{
   private static TETRIS instance;
    
   private TETRIS() {}
   
   public static void main(String[] args)
   {
       CONTROLLER controller = CONTROLLER.GetInstance();
       VIEW view = VIEW.GetInstance();
       MODEL model = MODEL.GetInstance();
       CLOCK clock = CLOCK.GetInstance();
       
       clock.AddObserver(controller);
       model.AddObserver(controller);
       view.AddObserver(controller);
       view.AddObserver(model);
       
       controller.SetView(view);
       controller.SetModel(model);
       controller.SetClock(clock);

       controller.Starten();
       
   }
   
   protected TETRIS GetInstance()
   {
       if(TETRIS.instance == null)
       {
           TETRIS.instance = new TETRIS();
       }
       return TETRIS.instance;
   }
   
}

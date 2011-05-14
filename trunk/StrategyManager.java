public class StrategyManager {
   public void run();
   
   // strategies en activite 
   private List<Strategy> runningStrategies;
   // strategies en attente 
   private List<Strategy> pendingStrategies; 
}

package fuzzycode;

import game.*;

import java.util.ArrayList;
import java.util.List;


public class StrategyManager implements FruitSaladAi {

	public static final Double MIN_ADQ_RESULT = 0.2;	
	// strategies en attente
	private List<Strategy> pendingStrategies;
	
	public StrategyManager(){
		// ajouter ici les différentes stratégies
		pendingStrategies = new ArrayList<Strategy>();
		//pendingStrategies.add( new Recupere() );
		pendingStrategies.add( new Sparta() );
		
	}
	
	private Integer[][] getWrap(int[][] primitifs){
		Integer[][] wrap;
		if(primitifs.length > 0) {
			wrap = new Integer[primitifs.length][primitifs[0].length];
			for (int i = 0; i < primitifs.length; i++) {
				for (int j = 0; j < primitifs[0].length; j++) {
					wrap[i][j] = primitifs[i][j];
				}
			}
		}
		else wrap = new Integer[0][0];
		
		return wrap;
	}
	
	private Integer[] getWrap(int[] primitifs){
		Integer[] wrap;
		if(primitifs.length > 0) {
			wrap = new Integer[primitifs.length];
			for (int i = 0; i < primitifs.length; i++) {
					wrap[i] = primitifs[i];
			}
		}
		else	wrap = new Integer[0];
		
		return wrap;
	}

	@Override
	public void mapUpdate(int[][] arg0, int[][] arg1) {
		Proxy.getProxy().updateMap(getWrap(arg0), getWrap(arg1));
	}

	@Override
	public void playTurn(int[][] arg0, int[] arg1, int[][] arg2, int[][] arg3, int[][] arg4) {
		List<Fruit> lf = Proxy.getProxy().getFruits(true);
		for (int i = 0; i < lf.size(); i++) {
			lf.get(i).setPa(2);
		}
		
		Proxy.getProxy().updateMap(getWrap(arg0), getWrap(arg1), getWrap(arg2), getWrap(arg3), getWrap(arg4));
		
		//Strategy.AdequacyResult bestAdqResult;
		List<Fruit> availableFruits = Proxy.getProxy().getFruits(true);
	//	Strategy bestStrategy;
		// choix des strategies
		/*do{
			bestAdqResult = null;
			bestStrategy = null;
			for(Strategy s : pendingStrategies ){
				Strategy.AdequacyResult adqResult = s.adequacy(availableFruits);   
				if((bestAdqResult == null)||(bestAdqResult.value < adqResult.value)){
					if(adqResult.value > MIN_ADQ_RESULT){
						bestStrategy = s;
						bestAdqResult = adqResult;
					}
				}
			}
			
			if(bestStrategy != null) {
				bestStrategy.run(); 
				availableFruits.removeAll(bestAdqResult.neededFruits);
			}
		} while(bestStrategy != null);*/
		pendingStrategies.get(0).computeAdequacy(availableFruits);
		pendingStrategies.get(0).run();
	}

	@Override
	public void stop(){} // JAVAjavajavajavaJAVAjavaJAVAjavaaaaa
	
	@Override
	public void initGame(int[][] arg0, int[][] arg1, int[][] arg2,	int limitCherry, int limitKiwi, int limitNut, int vitaminGoal, int maxNbTurns) {
		// creation de la map
		Proxy.getProxy().initMap(getWrap(arg0), getWrap(arg1), getWrap(arg2), limitCherry, limitKiwi, limitNut, vitaminGoal, maxNbTurns);
	}


}

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
		pendingStrategies.add( new Recupere() );
	}

	@Override
	public void mapUpdate(int[][] arg0, int[][] arg1) {
		Integer[][] leArg0 = new Integer[arg0.length][arg0[0].length];
		for (int i = 0; i < arg0.length; i++) {
			for (int j = 0; j < arg0[0].length; j++) {
				leArg0[i][j] = arg0[i][j];
			}
		}
		Integer[][] leArg1 = new Integer[arg1.length][arg1[0].length];
		for (int i = 0; i < arg1.length; i++) {
			for (int j = 0; j < arg1[0].length; j++) {
				leArg1[i][j] = arg1[i][j];
			}
		}
		
		Proxy.getProxy().updateMap(leArg0, leArg1);
	}

	@Override
	public void playTurn(int[][] arg0, int[] arg1, int[][] arg2, int[][] arg3, int[][] arg4) {
		List<Fruit> lf = Proxy.getProxy().getFruits(true);
		for (int i = 0; i < lf.size(); i++) {
			lf.get(i).setPa(2);
		}
		
		Integer[][] leArg0;
		if(arg0.length > 0) {
			leArg0 = new Integer[arg0.length][arg0[0].length];
			for (int i = 0; i < arg0.length; i++) {
				for (int j = 0; j < arg0[0].length; j++) {
					leArg0[i][j] = arg0[i][j];
				}
			}
		}
		else	leArg0 = new Integer[0][0];
		
		Integer[] leArg1 = new Integer[arg1.length];
		for (int i = 0; i < arg1.length; i++) {
			leArg1[i] = arg1[i];
		}
		
		Integer[][] leArg2;
		if(arg2.length > 0) {
			leArg2 = new Integer[arg2.length][arg2[0].length];
			for (int i = 0; i < arg2.length; i++) {
				for (int j = 0; j < arg2[0].length; j++) {
					leArg2[i][j] = arg2[i][j];
				}
			}
		}
		else	leArg2 = new Integer[0][0];
		
		Integer[][] leArg3;
		if(arg3.length > 0) {
			leArg3 = new Integer[arg3.length][arg3[0].length];
			for (int i = 0; i < arg3.length; i++) {
				for (int j = 0; j < arg3[0].length; j++) {
					leArg3[i][j] = arg3[i][j];
				}
			}
		}
		else	leArg3 = new Integer[0][0];
		
		Integer[][] leArg4;
		if(arg4.length > 0) {
			leArg4 = new Integer[arg4.length][arg4[0].length];
			for (int i = 0; i < arg4.length; i++) {
				for (int j = 0; j < arg4[0].length; j++) {
					leArg4[i][j] = arg4[i][j];
				}
			}
		}
		else	leArg4 = new Integer[0][0];
		
		Proxy.getProxy().updateMap(leArg0, leArg1, leArg2, leArg3, leArg4);
		
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
		Integer[][] architecture = new Integer[arg0.length][arg0[0].length];
		for (int i = 0; i < architecture.length; i++) {
			for (int j = 0; j < architecture[0].length; j++) {
				architecture[i][j] = arg0[i][j];
			}
		}
		Integer[][] fruits = new Integer[arg1.length][arg1[0].length];
		for (int i = 0; i < arg1.length; i++) {
			for (int j = 0; j < arg1[0].length; j++) {
				fruits[i][j] = arg1[i][j];
			}
		}
		Integer[][] buildings = new Integer[arg2.length][arg2[0].length];
		for (int i = 0; i < arg2.length; i++) {
			for (int j = 0; j < arg2[0].length; j++) {
				buildings[i][j] = arg2[i][j];
			}
		}
		// creation de la map
		Proxy.getProxy().initMap(architecture, fruits, buildings, limitCherry, limitKiwi, limitNut, vitaminGoal, maxNbTurns);
	}


}

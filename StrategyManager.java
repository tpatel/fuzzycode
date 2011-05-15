package fuzzycode;

import game.*;

import java.util.ArrayList;
import java.util.List;

import fuzzycode.Strategy;
import fuzzycode.Strategy.AdequacyResult;

public class StrategyManager implements FruitSaladAi {

	public static final Double MIN_ADQ_RESULT = 0.2;	
	// strategies en attente
	private List<Strategy> pendingStrategies;
	
	public StrategyManager(){
		// ajouter ici les différentes stratégies
		pendingStrategies = new ArrayList<Strategy>();
		pendingStrategies.add( new DummyStrategy() );
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
				leArg1[i][j] = arg0[i][j];
			}
		}
		Proxy.getProxy().updateMap(leArg0, leArg1);
	}

	@Override
	public void playTurn(int[][] arg0, int[] arg1, int[][] arg2, int[][] arg3, int[][] arg4) {
		Proxy.getProxy().updateMap(arg0, arg1, arg2, arg3, arg4);
		List<Fruit> lf = Proxy.getProxy().getFruits(true);
		for (int i = 0; i < lf.size(); i++) {
			lf.get(i).setPa(2);
		}
		
		List<AdequacyResult> strategiesToRun = new ArrayList<AdequacyResult>();
		
		Strategy.AdequacyResult bestAdqResult;
		List<Fruit> availableFruits = Proxy.getProxy().getFruits(true);
		Strategy bestStrategy;
		// choix des strategies
		do{
			bestAdqResult = null;
			bestStrategy = null;
			for( Strategy s : pendingStrategies ){
				Strategy.AdequacyResult adqResult = s.adequacy(availableFruits);   
				if((bestAdqResult == null)||(bestAdqResult.value < adqResult.value)){
					if(adqResult.value > MIN_ADQ_RESULT){
						bestStrategy = s;
						bestAdqResult = adqResult;
					}
				}
			}
			if( bestStrategy != null  ){ 
				bestStrategy.run(); 
				availableFruits.removeAll(bestAdqResult.neededFruits);
			}
		}while(bestStrategy != null);
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

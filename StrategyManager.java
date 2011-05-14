package fuzzycode;

import game.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fuzzycode.Strategy;

public class StrategyManager implements FruitSaladAi {

	// strategies en activite
	// private List<Strategy> runningStrategies;
	Strategy runningStrategy;
	// strategies en attente
	private List<Strategy> pendingStrategies;


	@Override
	public void mapUpdate(int[][] arg0, int[][] arg1) {
		Proxy.getProxy().updateMap(arg0, arg1);
	}

	@Override
	public void playTurn(int[][] arg0, int[] arg1, int[][] arg2, int[][] arg3, int[][] arg4) {
		
		// choix de la meilleure strategie
		// pour commencer on trouve juste la meilleure strategie, après on verra 
		// si on veut pouvoir en mettre plusieurs en même temps...
		runningStrategy.resetAdequacyCache();
		for( Strategy s : pendingStrategies ){
			s.resetAdequacyCache();
			if( s.adequacy() > runningStrategy.adequacy() ){
				runningStrategy = s;
			}
		}
		//lance la stratégie choisie;
		runningStrategy.run();
		
		
		/*
		Strategy bestStrategy = null;
		Double bestAdequacy = 0.0;
		for( Strategy s : pendingStrategies ){
			Double adequacy = s.adequacy();
			if((bestStrategy == null)||( adequacy > bestAdequacy )){
				bestStrategy = s;
				bestAdequacy = adequacy;
			}
		}
		Collections.sort(pendingStrategies, new Comparator<Strategy>(){
			@Override
			public int compare(Strategy arg0, Strategy arg1) {
				int result = arg0.adequacy().compareTo(arg1.adequacy());
			}} 
		);
		*/
	}

	@Override
	public void stop(){} // JAVAjavajavajavaJAVAjavaJAVAjavaaaaa

	@Override
	public void initGame(Integer[][] architecture, int[][] fruits, int[][] arg2, int buildings,	int limitCherry, int limitKiwi, int limitNut, int vitaminGoal, int maxNbTurns) {
		// creation de la map
		Proxy.getProxy().initMap(architecture, fruits, buildings, limitCherry, limitKiwi, limitNut, vitaminGoal, maxNbTurns);
		// ajouter ici les différentes stratégies
		pendingStrategies.add( new DummyStrategy() );
	}


}

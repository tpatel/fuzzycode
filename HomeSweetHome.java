package fuzzycode;

import game.Api;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class HomeSweetHome extends Strategy {
	private List<Fruit> availableFruits = new ArrayList<Fruit>();

	public String name() { return "HomeSweetHome"; }

	public void eachFrame(){  }
	
	@Override
	public AdequacyResult computeAdequacy(List<Fruit> availableFruits) {
		boolean tookOne = false;
		int remainingTurns = Proxy.getProxy().getMaxNbTurns() - Proxy.getProxy().getCurrentTurn();
		int posBaseX = Proxy.getProxy().getBuildingFriend(Api.BUILDING_SUGAR_BOWL).x;
		int posBaseY = Proxy.getProxy().getBuildingFriend(Api.BUILDING_SUGAR_BOWL).y;
		for(Fruit fruit : availableFruits){
			if(fruit.getCurVitamins() > 0){
				int nbTurnsFromHome = ( Math.abs( fruit.getX() - posBaseX ) + Math.abs( fruit.getY() - posBaseY ) )/fruit.getSpeed();
				if( remainingTurns - nbTurnsFromHome < 4 ){
					if(!this.availableFruits.contains(fruit)){
						this.availableFruits.add(fruit);
						tookOne = true;
					}
				}
			}
		}
		if(tookOne)
			return new AdequacyResult(100.0, this.availableFruits);
		else
			return new AdequacyResult(0.0, this.availableFruits);
	}

	@Override
	public void run() {
		System.out.println("\n\n");
		System.out.println(" ON MY WAY HOME YOUHOOOOOOOOOOOOOOOOOOOOOOOOOO !!!");
		System.out.println("\n\n");
		for(Fruit f : this.availableFruits) {
			while (f.getPa() > 0) {
				
			Point dest = Proxy.getProxy().getBuildingFriend(Api.BUILDING_SUGAR_BOWL);
				
				List<Node> list = findPath(f.getX(), f.getY(), dest.x, dest.y, true);
				if (list == null) {
					// On ne fait rien
					f.setPa(f.getPa() - 1);
				} else {
					if (list.size() == 0) {
						if (Proxy.getProxy().stockSugar(f) != Api.OK) f.setPa(f.getPa() - 1);
					} else {
						Integer positionX = list.get(Math.min(list.size(), f.getSpeed()) - 1).x;
						Integer positionY = list.get(Math.min(list.size(), f.getSpeed()) - 1).y;
						if (Proxy.getProxy().move(f, positionX, positionY) != Api.OK)
							f.setPa(f.getPa() - 1);
					}
				}
			}
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}

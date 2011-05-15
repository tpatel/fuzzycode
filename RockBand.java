package fuzzycode;

import game.Api;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class RockBand extends Strategy {
	private List<Fruit> availableFruits = new ArrayList<Fruit>();

	public String name() { return "RockBand"; }
	
	@Override
	public AdequacyResult computeAdequacy(List<Fruit> availableFruits) {
		
		Fruit closestEnemy = closestFruit(
				(int)Math.round( proxy.getProxy().getBuildingNeutral(Api.BUILDING_VITAMIN_SOURCE).getX() )
				, (int)Math.round( proxy.getProxy().getBuildingNeutral(Api.BUILDING_VITAMIN_SOURCE).getX() )
				, proxy.getProxy().getFruits(false));
		
		
		
		
		this.availableFruits.addAll(availableFruits);
		return new AdequacyResult(0.8, availableFruits);
	}

	@Override
	public void run() {
		List<Fruit> lf = availableFruits;

		for (int k = 0; k < lf.size(); k++) {
			Fruit f = lf.get(k);
			while (f.getPa() > 0) {
				
				Integer typeBuilding = null;
				if (f.getCurVitamins() == 0) {
					typeBuilding = Api.BUILDING_VITAMIN_SOURCE;
				} else {
					typeBuilding = Api.BUILDING_SUGAR_BOWL;
				}
				Point dest = null;
					
				if (typeBuilding == Api.BUILDING_VITAMIN_SOURCE) {
					dest = Proxy.getProxy().getBuildingNeutral(typeBuilding);					
				} else {
					dest = Proxy.getProxy().getBuildingFriend(typeBuilding);
				}
				
				List<Node> list = findPath(f.getX(), f.getY(), dest.x, dest.y, true);
				if (list == null) {
					// On ne fait rien
					f.setPa(f.getPa() - 1);
				} else {
					if (list.size() == 0) {
						if (typeBuilding == Api.BUILDING_VITAMIN_SOURCE) {
							Proxy.getProxy().drawVitamin(f);
						}
						else 
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

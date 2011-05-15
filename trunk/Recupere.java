package fuzzycode;

import game.Api;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Recupere extends Strategy {
	private List<Fruit> availableFruits = new ArrayList<Fruit>();

	public String name() { return "Recupere"; }

	public void eachFrame(){ availableFruits = new ArrayList<Fruit>(); }
	
	@Override
	public AdequacyResult computeAdequacy(List<Fruit> availableFruits) {
		if( this.availableFruits.size() > Proxy.getProxy().getFruits(true).size() / 2 ){
			int debug1 = Proxy.getProxy().getFruits(true).size() / 2;
			int debug2 = this.availableFruits.size();
			System.out.println("dygvchcd >>> <<<< >>> <<<< >>< --------" );
			System.out.println(debug1);
			System.out.println(debug2);
			System.out.printf("dygvchcd >>> <<<< >>> <<<< >>< --------" );
			return new AdequacyResult(0.4, availableFruits ); 
		}else{
			if(!this.availableFruits.contains(availableFruits.get(0)))
				this.availableFruits.add(availableFruits.get(0));
			System.out.printf("--------------------- %n \n", this.availableFruits.size() );
			return new AdequacyResult(1.0, this.availableFruits);
		}
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

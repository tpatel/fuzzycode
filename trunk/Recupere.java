package fuzzycode;

import game.Api;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Recupere extends Strategy {
	private List<Fruit> availableFruits = new ArrayList<Fruit>();

	@Override
	public AdequacyResult computeAdequacy(List<Fruit> availableFruits) {
		this.availableFruits.addAll(availableFruits);
		return new AdequacyResult(1.0, availableFruits);
	}

	@Override
	public void run() {
		List<Fruit> lf = availableFruits;
		// for (int i = 0; i < availableFruits.size(); i++) {
		// System.out.println(availableFruits.get(i).isFriend() + " "
		// + availableFruits.get(i).id + " "
		// + availableFruits.get(i).getX());
		// }
		for (int k = 0; k < lf.size(); k++) {
			Fruit f = lf.get(k);
			while (f.getPa() > 0) {
				if (f.getCurVitamins() == 0) {
					Point dest = Proxy.getProxy().getBuildingNeutral(Api.BUILDING_VITAMIN_SOURCE);
					List<Node> list = findPath(f.getX(), f.getY(), dest.x,
							dest.y, true);
					if (list == null) {
						// On ne fait rien
						f.setPa(f.getPa() - 1);
					} else {
						if (list.size() == 0) {
							Proxy.getProxy().drawVitamin(f);
						} else {
							Integer positionX = list.get(Math.min(list.size(), f.getSpeed()) - 1).x;
							Integer positionY = list.get(Math.min(list.size(), f.getSpeed()) - 1).y;
							Proxy.getProxy().move(f,
									list.get(list.size() - 1).x,
									list.get(list.size() - 1).y);
						} 
						
					}
				} else {
					Point dest = Proxy.getProxy().getBuildingFriend(Api.BUILDING_SUGAR_BOWL);
					List<Node> list = findPath(f.getX(), f.getY(), dest.x,
							dest.y, true);
					if (list == null) {
						// On ne fait rien
						f.setPa(f.getPa() - 1);
					} else {
						if (list.size() == 0) {
							Proxy.getProxy().stockSugar(f);
						} else if (list.size() < f.getSpeed()) {
							Proxy.getProxy().move(f,
									list.get(list.size() - 1).x,
									list.get(list.size() - 1).y);
						} else {
							Proxy.getProxy().move(f,
									list.get(f.getSpeed() - 1).x,
									list.get(f.getSpeed() - 1).y);
						}
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

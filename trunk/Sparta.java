package fuzzycode;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import fuzzycode.Strategy.AdequacyResult;
import fuzzycode.Strategy.Node;
import game.Api;

public class Sparta extends Strategy {
	private List<Fruit> availableFruits = new ArrayList<Fruit>();

	@Override
	public AdequacyResult computeAdequacy(List<Fruit> availableFruits) {
		this.availableFruits.addAll(availableFruits);
		return new AdequacyResult(1.0, availableFruits);
	}

	@Override
	public void run() {
		List<Fruit> lf = availableFruits;

		for (int k = 0; k < lf.size(); k++) {
			Fruit f = lf.get(k);
			while (f.getPa() > 0) {
				
				Point dest = new Point();
				
				List<Fruit> enemies = Proxy.getProxy().getFruits(false);
				List<Node> path = null; Fruit target = null;
				for (Fruit e : enemies) {
					System.out.println("Enemy: "+e.x+" "+e.y);
					dest.x = e.x; dest.y = e.y;
					List<Node> list = findPath(f.getX(), f.getY(), dest.x, dest.y, true);
					if (list != null && path != null && list.size() < path.size()) {
						path = list;
						target = e;
					}
				}
				
				if (path == null) {
					// On ne fait rien
					f.setPa(f.getPa() - 1); // TODO: aller à la vitamine
				} else {
					if (path.size() <= f.getRange()) {
						/*if(f.hasEquipment()) {
							if (Proxy.getProxy().useEquipment(f, f.getEquipments().get(0), e) != Api.OK) f.setPa(f.getPa() - 1);
						} else {*/
						int i = Proxy.getProxy().attack(f, target);
						if (i < 0 || i != Api.HIT) f.setPa(f.getPa() - 1);
						//}
					} else {
						Integer positionX = path.get(Math.min(path.size(), f.getSpeed()) - f.getRange()).x;
						Integer positionY = path.get(Math.min(path.size(), f.getSpeed()) - f.getRange()).y;
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

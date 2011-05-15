package fuzzycode;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import fuzzycode.Strategy.AdequacyResult;
import fuzzycode.Strategy.Node;
import game.Api;

public class Sparta extends Strategy {
	private List<Fruit> availableFruits = new ArrayList<Fruit>();

	boolean activated = true;
	static final int SPARTA_AREA_LIMIT = 700;

	public Sparta() {
		activated = (Proxy.getProxy().getMapWidth()
				* Proxy.getProxy().getMapHeight() < SPARTA_AREA_LIMIT);

	}

	public String name() {
		return "Sparta";
	}

	@Override
	public AdequacyResult computeAdequacy(List<Fruit> availableFruits) {
		if (activated) {
			this.availableFruits.addAll(availableFruits);
			return new AdequacyResult(0.9, availableFruits);
		} else {
			return new AdequacyResult( 0.0, new ArrayList<Fruit>() );
		}
	}

	@Override
	public void run() {
		List<Fruit> lf = availableFruits;
		
		for (int k = 0; k < lf.size(); k++) {
		//for (int k = 0; k < 1; k++) {
				Fruit f = lf.get(k);
			while (f.getPa() > 0) {

				Point dest = new Point();

				List<Fruit> enemies = Proxy.getProxy().getFruits(false);
				//List<Node> path = null;
				List<Point> path = null;
				Fruit target = null;
				for (Fruit e : enemies) {
					System.out.println("Enemy: "+e.x+" "+e.y);
					dest.x = e.x; dest.y = e.y;
					//List<Node> list = findPath(f.getX(), f.getY(), dest.x, dest.y, true);
					List<Point> list = findPath(f.getX(), f.getY(), dest.x, dest.y);
					for(int i = 0 ; i < list.size() ; i++) {
						System.out.println(list.get(i));
					}
					if ((list != null) && (path == null || list.size() < path.size())) {
						path = list;	//System.out.println("OOMMMAGAAD");
						target = e;
					}
				}
				if (path != null) System.out.println("enemy spotted: "+target.x+"/"+target.y);
				if (path == null) {
					// On ne fait rien
					f.setPa(f.getPa() - 1); // TODO: aller à la vitamine
				} else {
					/*boolean shootable = bresenham(f.x,f.y,target.x,target.y);
					if (path.size() <= f.getRange() && shootable) {
						//if(f.hasEquipment()) {
						//	if (Proxy.getProxy().useEquipment(f, f.getEquipments().get(0), e) != Api.OK) f.setPa(f.getPa() - 1);
						//} else {
						//if (bresenham(proxy,f.x,f.y,target.x,target.y)) {
						System.out.println("THIS IS SPARTAAA!!");
							int i = Proxy.getProxy().attack(f, target);
							if (i < 0 || i != Api.HIT) f.setPa(f.getPa() - 1);
							System.out.println(">>> "+Api.decode(i));
							if (i>0) {
								System.out.println("Je l'ai tué!");
								enemies.remove(target);
							}
						//} else f.setPa(f.getPa() - 1);
						//}
					} else {
						System.out.println("moving...");
						int ind = Math.min(path.size()-1, Math.max(f.getSpeed()-f.getRange()-1+(shootable?0:1),0));
						//System.out.println(":::::::::::"+ind);
						Integer positionX = path.get(ind).x;
						Integer positionY = path.get(ind).y;
						if (Proxy.getProxy().move(f, positionX, positionY) != Api.OK)
							f.setPa(f.getPa() - 1);
					}
					*/
					//System.out.println(path.size()+" "+(f.getSpeed() - 1));
					if (path.size()==0) {
						int i = Proxy.getProxy().attack(f, target);
						if (i < 0 || i != Api.HIT) f.setPa(f.getPa() - 1);
						System.out.println(">>> "+Api.decode(i)+"  "+i);
						if (i>=0) {
							System.out.println("Je l'ai tué!");
							enemies.remove(target);
							
						}
					} else {
						Integer r = 0, i = 1;
						do {
							Integer positionX = path.get(Math.max(0, Math.min(path.size(), f.getSpeed()) - i)).x;
							Integer positionY = path.get(Math.max(0, Math.min(path.size(), f.getSpeed()) - i)).y;
							r = Proxy.getProxy().move(f, positionX, positionY);
							System.out.println("" + positionX + " " + positionY);
							i++;
						} while(r == Api.TOO_FAR && i < 42);
						if(r != Api.OK)	f.setPa(f.getPa() - 1);
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

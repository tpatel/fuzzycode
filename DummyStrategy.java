package fuzzycode;

import java.util.*;

public class DummyStrategy extends Strategy {

	@Override
	public AdequacyResult adequacy(List<Fruit> fruits) {
		return new AdequacyResult(1.0, fruits);
	}

	@Override
	public void run() {
		/*for(Integer j = Proxy.getProxy().getMapHeight() - 1 ; j >= 0 ; j--) {
			for(Integer i = 0 ; i < Proxy.getProxy().getMapWidth() ; i++) {
				Cell c = Proxy.getProxy().getCell(i, j);
				//System.out.println("" + i + " " + j + " " + c + " " + c.isWall());
				if(c.isWall())				System.out.print("#");
				else if(c.hasEquipments())	System.out.print("E");
				else if(c.hasBuilding())	System.out.print("B");
				else if(c.hasChest())		System.out.print("C");
				else if(c.hasFruit()) {
					if(c.getFruit().isFriend())	System.out.print("F");
					else						System.out.print("Z");
				}
				else if(c.hasSugar())		System.out.print("S");
				else						System.out.print(" ");
			}
			System.out.println();
		}*/

		//System.out.println("Run");
		List<Fruit> lf = Proxy.getProxy().getFruits(true);
		for (int i = 0; i < lf.size(); i++) {
			Fruit f = lf.get(i);
			for (int l = 0; l < 2; l++) {
					System.out.println("goo");
				int min = 10000;
				int maxk = 0;
				int maxj = 0;
				for(int k=-f.getSpeed(); k<=f.getSpeed(); k++) {
					for(int j=-f.getSpeed(); j<=f.getSpeed(); j++) {
						if(f.getX()+k<0 || f.getX()+k >= Proxy.getProxy().getMapWidth() || f.getY()+j < 0 || f.getY()+j >= Proxy.getProxy().getMapHeight()) continue;
						if(!Proxy.getProxy().getCell(f.getX()+k, f.getY()+j).isOccupied()
								&& Proxy.getProxy().getCell(f.getX()+k, f.getY()+j).getStrategicInterest()<min
								&& Proxy.getProxy().getCell(f.getX(), f.getY()).getStrategicInterest() - Proxy.getProxy().getCell(f.getX()+k, f.getY()+j).getStrategicInterest() <= f.getSpeed()) {
							maxk = k;
							maxj = j;
							min = Proxy.getProxy().getCell(f.getX()+k, f.getY()+j).getStrategicInterest();
							System.out.println("better " + min);
						}
					}
				}
				if(maxk != 0 || maxj != 0) {
					Proxy.getProxy().move(f, f.getX()+maxk, f.getY()+maxj);
					System.out.println("Bouge " + i);
				} else {
					System.out.println("Raté !");
				}
			}
		}
	}
	
	class Triplet {
		public int x;
		public int y;
		public int val;
		public Triplet(int x_, int y_, int val_) {
			x = x_;
			y = y_;
			val = val_;
		}
	}
	
	@Override
	public void start() {
		System.out.println("Debut");
		LinkedList<Triplet> queue = new LinkedList<Triplet>();
		queue.addFirst(new Triplet(10, 6, 1)); //TODO: position de l'arbre à vitamine à récupérer
		
		while(queue.size() != 0) {
			Triplet t = queue.remove();
			
			if(Proxy.getProxy().getCell(t.x, t.y).getStrategicInterest() == 0) {
				if(Proxy.getProxy().getCell(t.x, t.y).isWall()) {
					Proxy.getProxy().getCell(t.x, t.y).setStrategicInterest(10000); //FIXME: infini
				} else {
					Proxy.getProxy().getCell(t.x, t.y).setStrategicInterest(t.val);
					for(int i=-1; i<=1; i++) {
						for(int j=-1; j<=1; j++) {
							if(i==0 && j==0) continue;
							queue.addLast(new Triplet(t.x+i, t.y+j, t.val+1));
						}
					}
				}
			}
		}
		
		for (int i = 0; i < Proxy.getProxy().getMapWidth(); i++) {
			for (int j = 0; j < Proxy.getProxy().getMapHeight(); j++) {
				System.out.print("\t"+Proxy.getProxy().getCell(i, j).getStrategicInterest());
			}
			System.out.println();
		}
	}

	@Override
	public void stop() {
		System.out.println("Fin");
	}

	@Override
	public AdequacyResult computeAdequacy(List<Fruit> availableFruits) {
		// TODO Auto-generated method stub
		return null;
	}

}

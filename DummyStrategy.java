package fuzzycode;

import java.util.*;

public class DummyStrategy extends Strategy {

	@Override
	public Double adequacy() {
		return 1.0;
	}

	@Override
	public void run() {
		for(Integer j = Proxy.getProxy().getMapHeight() - 1 ; j >= 0 ; j--) {
			for(Integer i = 0 ; i < Proxy.getProxy().getMapWidth() ; i++) {
				Cell c = Proxy.getProxy().getCell(i, j);
				//System.out.println("" + i + " " + j + " " + c + " " + c.isWall());
				if(c.isWall())				System.out.print("#");
				else if(c.hasEquipments())	System.out.print("E");
				else if(c.hasBuilding())	System.out.print("B");
				else if(c.hasChest())		System.out.print("C");
				else if(c.hasFruit())		System.out.print("F");
				else if(c.hasSugar())		System.out.print("S");
				else						System.out.print(" ");
			}
			System.out.println();
		}
		
		
		//System.out.println("Run");
		List<Fruit> lf = Proxy.getProxy().getFruits(true);
		for (int i = 0; i < lf.size(); i++) {
			Fruit f = lf.get(i);
			Proxy.getProxy().move(f, f.getX()+(Math.random()>0.5 ? 2 : -2), f.getY()+(Math.random()>0.5 ? 2 : -2));
		}
		 
	}

	@Override
	public void start() {
		System.out.println("Debut");
	}

	@Override
	public void stop() {
		System.out.println("Fin");
	}

	@Override
	public Double computeAdequacy() {
		return 1.0;
	}

}

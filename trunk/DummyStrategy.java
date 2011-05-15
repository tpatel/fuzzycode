package fuzzycode;

public class DummyStrategy extends Strategy {

	@Override
	public Double computeAdequacy() {
		// TODO Auto-generated method stub
		return 0.5;
	}


	@Override
	public void run() {
		for(Integer i = 0 ; i < Proxy.getProxy().getMapWidth() ; i++) {
			for(Integer j = 0 ; j < Proxy.getProxy().getMapHeight() ; j++) {
				Cell c = Proxy.getProxy().getCell(i, j);
				if(c.isWall())	System.out.print("#");
				else if(c.hasEquipments())	System.out.print("E");
				else if(c.hasBuilding())	System.out.print("B");
				else if(c.hasChest())		System.out.print("C");
				else if(c.hasFruit())		System.out.print("F");
				else if(c.hasSugar())		System.out.print("S");
				else						System.out.print(" ");
			}
			System.out.println();
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

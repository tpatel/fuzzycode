package fuzzycode;

import java.util.*;

public class DummyStrategy extends Strategy {

	@Override
	public Double adequacy() {
		return 1.0;
	}

	@Override
	public void run() {
		System.out.println("Run");
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

package fuzzycode;

import java.util.*;

/**
 * Interface des stagtegies.
 */
public abstract class Strategy {
	public Strategy() {
		this.proxy = Proxy.getProxy();
		this.oncePerFrame = true;
		this.cachedAdequacy = new AdequacyResult(0.0, new ArrayList<Fruit>());
	}

	public class AdequacyResult {
		public AdequacyResult(Double value, List<Fruit> neededFruits) {
			this.value = value;
			this.neededFruits = neededFruits;
		}

		public Double value;
		public List<Fruit> neededFruits;
	}

	/**
	 * Appelé a l'activation de la strategie.
	 */
	public abstract void start();

	/**
	 * Appelé a la desactivation de la strategie.
	 */
	public abstract void stop();

	/**
	 * Appelé a chaque frame si la strategie est active.
	 */
	public abstract void run();

	/**
	 * Pertinence de la strategie.
	 */
	public abstract AdequacyResult computeAdequacy(List<Fruit> availableFruits);

	/**
	 * Les methodes qui suivent servent juste a eviter que la pertincence soit
	 * calculée plus d'une fois par tour.
	 */
	public void resetAdequacyCache() {
		oncePerFrame = true;
	}

	/**
	 * renvoie la pertinence, c'est cette méthode qu'il faut appeler et non
	 * computeAdequacy.
	 */
	public AdequacyResult adequacy(List<Fruit> availableFruits) {
		if (oncePerFrame) {
			cachedAdequacy = computeAdequacy(availableFruits);
			oncePerFrame = false;
		}
		return cachedAdequacy;
	}

	private Boolean oncePerFrame;
	private AdequacyResult cachedAdequacy;

	// static final int INFINITY = Integer.MAX_VALUE;
	static final int[][] neighbourPattern = { { -1, -1 }, { 0, -1 }, { 1, -1 },{ -1, 0 }, { 1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };
	protected Proxy proxy;

	@SuppressWarnings("unchecked")
	protected class Node implements Comparable {
		static final int INFINITY = Integer.MAX_VALUE;
		int x, y, d = INFINITY;
		Node prev = null;
		boolean visited = false, isPenetrable;

		public Node(int _x, int _y) {
			x = _x;
			y = _y;
		}

		@Override
		public int compareTo(Object arg0) {
			return d - ((Node) arg0).d;
		}
	}

	@SuppressWarnings("unchecked")
	protected List<Node> findPath(int x1, int y1, int x2, int y2,
			boolean building) { // System.out.println("isOcc "+proxy.getCell(0,0).isOccupied());
		int w = proxy.getMapWidth();
		int h = proxy.getMapHeight();
		Node[][] aNodes = new Node[w][h];
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				aNodes[i][j] = new Node(i, j);
				nodes.add(aNodes[i][j]);
				aNodes[i][j].isPenetrable = !proxy.getCell(i, j).isOccupied();
			}
		}
		aNodes[x1][y1].d = 0;
		Node end = null;
		while (nodes.size() != 0) {
			Collections.sort(nodes);
			Node u = nodes.get(0);

			//System.out.println("currentNode: " + u.x + " " + u.y + "  " + u.d);

			if (u.d == Node.INFINITY)
				break;
			if (u.x == x2 && u.y == y2) {
				end = u;
				break;
			}
			nodes.remove(0);
			int alt = u.d + 1;
			for (int i = 0; i < neighbourPattern.length; i++) {
				int x = u.x + neighbourPattern[i][0], y = u.y
						+ neighbourPattern[i][1];
				// System.out.println("isPen "+x+" "+y+"  "+aNodes[x][y].isPenetrable);
				if (x >= 0
						&& y >= 0
						&& x < w
						&& y < h
						&& (aNodes[x][y].isPenetrable || (building && x == x2 && y == y2))) {
					Node n = aNodes[x][y];
					if (alt < n.d) {
						n.d = alt;
						n.prev = u;
					}
				}
			}
		}
		if (end == null)
			return null;
		else {
			nodes = new ArrayList<Node>();
			while (end.prev != null) {
				// nodes.add(end);
				nodes.add(0, end);
				end = end.prev;
			}
			if (building)
				nodes.remove(nodes.size() - 1);
			return nodes;
		}
	}
	
	public static Boolean bresenham(Proxy proxy, Integer x0, Integer y0, Integer x1, Integer y1) {
		int dx=Math.abs(x1-x0), dy=Math.abs(y1-y0);
		int sx = (x0<x1?1:-1), sy = (y0<y1?1:-1);
		int err = dx-dy;
		while (x0!=x1 || y0!=y1) {
			if(proxy.getCell(x0, y0).isOccupied())
				return true;
			int e2 = 2*err;
			if (e2>-dy) {err -= dy; x0 += sx;}
			if (e2<dx) {err += dx; y0 += sy;}
			}
		return false;
	}
	

	protected Fruit closestFruit(int x, int y, List<Fruit> fruits){
		Integer closestDistance = 99999;
		Fruit closestFruit = null;
		for(Fruit f:fruits){
			Integer d = Math.min(Math.abs(x - f.getX()), Math.abs(y - f.getY()));
			if(d < closestDistance){
				closestDistance = d;
				closestFruit = f;
			}
		}
		return closestFruit;
	}
	
	protected List<Fruit> getFruitsInRegion(int x, int y, int radius, List<Fruit> availableFruits){
		List<Fruit> result = new ArrayList<Fruit>();
		for(Fruit fruit : availableFruits){
			if( Math.min(Math.abs(x - fruit.getX()), Math.abs(y - fruit.getY())) < radius ){
				result.add(fruit);
			}
		}
		return result;
	}
	
	protected void updateDangerLevel() {
		Proxy ProxyAPI = Proxy.getProxy();
		List<Fruit> enemies = ProxyAPI.getFruits(false);
		for (int y = 0; y < ProxyAPI.getMapHeight(); ++y) {
			for (int x = 0; x < ProxyAPI.getMapWidth(); ++x) {
				for (Fruit enemy : enemies) {
					if (Math.abs(x - enemy.getX()) < enemy.getRange()) {
						if (Math.abs(y - enemy.getY()) < enemy.getRange()) {
							// TODO
						}
					}
				}
			}
		}

	}

}

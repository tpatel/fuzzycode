package fuzzycode;

import java.awt.Point;
import java.util.*;

/**
 * Interface des stagtegies.
 */
public abstract class Strategy {
	public Strategy() {
		this.oncePerFrame = true;
		this.cachedAdequacy = new AdequacyResult(0.0, new ArrayList<Fruit>());
	}

	public void eachFrame(){ }
	
	public String name(){ return "Strategy"; }
	
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
			//oncePerFrame = false;
		}
		return cachedAdequacy;
	}

	private Boolean oncePerFrame;
	private AdequacyResult cachedAdequacy;

	// static final int INFINITY = Integer.MAX_VALUE;
	static final int[][] neighbourPattern = { { -1, -1 }, { 0, -1 }, { 1, -1 },{ -1, 0 }, { 1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };


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
	
	protected int distHeuristique(int x1, int y1, int x2, int y2) {
		return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
	}

	/*@SuppressWarnings("unchecked")
	protected List<Node> findPath(int x1, int y1, int x2, int y2,
			boolean building) { // System.out.println("isOcc "+proxy.getCell(0,0).isOccupied());
		int w = Proxy.getProxy().getMapWidth();
		int h = Proxy.getProxy().getMapHeight();
		Node[][] aNodes = new Node[w][h];
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				aNodes[i][j] = new Node(i, j);
				nodes.add(aNodes[i][j]);
				aNodes[i][j].isPenetrable = !Proxy.getProxy().getCell(i, j).isOccupied();
			}
		}
		aNodes[x1][y1].d = 0;
		Node end = null;
		Integer  t= 0;
		while (nodes.size() != 0 && t < 200) {
			t++;
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
	}*/

	protected List<Point> findPath(int x1, int y1, int x2, int y2) {
		AStar as = new AStar(new Point(x1, y1), new Point(x2, y2), 10);
		return as.calculPath();
	}
	
	
	
	public static Boolean bresenham(Integer x0, Integer y0, Integer x1, Integer y1) {
		int dx=Math.abs(x1-x0), dy=Math.abs(y1-y0);
		int sx = (x0<x1?1:-1), sy = (y0<y1?1:-1);
		int err = dx-dy;
		while (x0!=x1 || y0!=y1) {
			if(Proxy.getProxy().getCell(x0, y0).isOccupied())
				return false;
			int e2 = 2*err;
			if (e2>-dy) {err -= dy; x0 += sx;}
			if (e2<dx) {err += dx; y0 += sy;}
			}
		return true;
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
	
	public static class AStar {
		public static Integer distHeur(Point p0, Point p1) {
			return Math.max(Math.abs(p1.x - p0.x), Math.abs(p1.y - p0.y));
		}
		
		private List<Point> closedList;
		private List<Point> openList;
		private Map<Point, Point> visitedList;
		
		private Map<Point, Integer> distHeur;
		private Map<Point, Integer> distReal;
		private Map<Point, Integer> distEstim;
		
		private Point begin;
		private Point end;
		private Integer range;
		
		public AStar(Point begin, Point end, Integer range) {
			this.closedList = new ArrayList<Point>();
			this.openList = new ArrayList<Point>();
			this.visitedList = new HashMap<Point, Point>();
			
			this.distEstim = new HashMap<Point, Integer>();
			this.distReal = new HashMap<Point, Integer>();
			this.distHeur = new HashMap<Point, Integer>();
			
			this.distHeur.put(begin, distHeur(begin, end));
			this.distReal.put(begin, 0);
			this.distEstim.put(begin, distHeur(begin, end));
			
			this.begin = begin;
			this.end = end;
			this.range = range;
			
			this.openList.add(this.begin);
		}
		
		public List<Point> calculPath() {
			while(!this.openList.isEmpty()) {
				Integer min = this.distEstim.get(openList.get(0));
				Point pMin = openList.get(0);
				/*for(Map.Entry<Point, Integer> e : distEstim.entrySet()) {
					if(e.getValue() < min) {
						min = e.getValue();
						pMin = e.getKey();
					}
				}*/
				for (int i = 0; i < this.openList.size(); i++) {
					for(Map.Entry<Point, Integer> e : distEstim.entrySet()) {
						if(openList.get(i).x == e.getKey().x && openList.get(i).y == e.getKey().y && e.getValue() < min) {
							min = e.getValue();
							pMin = e.getKey();
							//System.out.println("min");
						}
					}
				}
				
				if(distHeur(pMin, this.end) <= this.range){
					System.out.println(pMin);
					return AStar.getPath(this.visitedList, this.visitedList.get(begin));
				}
				
				//System.out.print(" ( " + this.openList.size() + " " + this.closedList.size() + " " + distEstim.size() + "|");
				this.closedList.add(pMin);
				//boolean retour = this.openList.remove(pMin);
				int ind = -1;
				for (int j = 0; j < this.openList.size(); j++) {
					if(openList.get(j).x == pMin.x && openList.get(j).y == pMin.y) {
						ind = j;
					}
				}
				Point retour = new Point(42, 42);
				if(ind >=0) retour = this.openList.remove(ind);
				//System.out.print(" | " + this.openList.size() + " " + this.closedList.size() + ") " + retour);
				
				Point[] neighbours = {new Point(pMin.x - 1, pMin.y), new Point(pMin.x, pMin.y - 1), new Point(pMin.x - 1, pMin.y - 1), new Point(pMin.x + 1, pMin.y), new Point(pMin.x, pMin.y + 1), new Point(pMin.x + 1, pMin.y + 1), new Point(pMin.x - 1, pMin.y + 1), new Point(pMin.x + 1, pMin.y - 1)};
				for(int i = 0 ; i < 8 ; i++) {
					//if(this.closedList.contains(neighbours[i]))	continue;
					boolean result = false;
					for (int j = 0; j < this.closedList.size(); j++) {
						if(closedList.get(j).x == neighbours[i].x && closedList.get(j).y == neighbours[i].y) {
							result = true;
							break;
						}
					}
					if(result) continue;
					if(Proxy.getProxy().getCell(neighbours[i].x, neighbours[i].y).isOccupied())	continue;
					Integer distReal = this.distReal.get(pMin) + 1; //Par la suite on pourra influencer le trajet par la presence d'alliés ou d'ennemis
					
					Boolean pass = false;
					for (int j = 0; j < this.openList.size(); j++) {
						if(openList.get(j).x == neighbours[i].x && openList.get(j).y == neighbours[i].y) {
							pass = true;
							break;
						}
					}
					if(!pass) {
						openList.add(neighbours[i]);
						pass = true;
					}
					else if(distReal < this.distReal.get(neighbours[i]))
						pass = true;
					
					if(pass) {
						this.visitedList.put(neighbours[i], pMin);
						this.distReal.put(neighbours[i], distReal);
						this.distHeur.put(neighbours[i], distHeur(neighbours[i], this.end));
						this.distEstim.put(neighbours[i], this.distReal.get(neighbours[i]) + this.distHeur.get(neighbours[i]));
					}
				}
			}
			
			return null;
		}
		
		public static List<Point> getPath(Map<Point, Point> visitedList, Point currentNode) {
			if(visitedList.get(currentNode) != null) {
				List<Point> l = (getPath(visitedList, visitedList.get(currentNode)));
				l.add(currentNode);
				return l;
			}
			else {
				List<Point> l = new ArrayList<Point>();
				l.add(currentNode);
				return l;
			}
		}
	}

}

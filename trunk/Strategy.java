package fuzzycode;

import java.util.*;

/**
 * Interface des stagtegies.
 */
public abstract class Strategy {
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
    public abstract Double adequacy();
    
    static final int[][] neighbourPattern = {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}};
    protected Proxy proxy;
    protected class Node implements Comparable {
    	static final int INFINITY = Integer.MAX_VALUE;
        int x,y,d=INFINITY; boolean visited = false; Node prev = null;
        
        public Node(int _x, int _y){ x=_x; y=_y; }
        
    	@Override
    	public int compareTo(Object arg0) {
    		return ((Node)arg0).d-d;
    	}
    }
    
    protected List<Node> findPath(int x1,int y1,int x2,int y2) {
    	int w = proxy.getMapWitdh();
    	int h = proxy.getMapHeight();
    	Node[][] aNodes = new Node[w][h]; List<Node> nodes = new ArrayList<Node>();
    	for (int i=0; i<w; i++) for (int j=0; j<h; j++) { aNodes[i][j] = new Node(i,j); nodes.add(aNodes[i][j]); }
    	aNodes[x1][y1].d = 0;
    	Node end=null;
    	while (nodes.size()!=0) {
        	Collections.sort(nodes);
        	Node u = nodes.get(0);
        	if (u.d == Node.INFINITY) break;
        	if (u.x==x2 && u.y==y2) {end = u; break;};
        	nodes.remove(0);
    		int alt = u.d+1;
        	for (int i = 0; i<neighbourPattern.length; i++) {
        		int x = neighbourPattern[i][0], y = neighbourPattern[i][1];
        		if (x>=0 && y>=0 && x<w && y<h) {
        			Node n = aNodes[x][y];
        			if (alt<n.d) { n.d = alt; n.prev = u; }
        		}
        	}
    	}
    	if (end==null) return null;
    	else {
    		nodes = new ArrayList<Node>();
    		while(end.prev!=null){
    			nodes.add(end);
    			end = end.prev;
    		}
    		return nodes;
    	}
    }
    
}

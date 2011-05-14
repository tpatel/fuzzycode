package fuzzycode;

public class ShootPath {
	public static Boolean test(Proxy proxy, Integer x0, Integer y0, Integer x1, Integer y1) {
		Integer dx = Math.abs(x1 - x0), dy=Math.abs(y1 - y0);
		Integer sx = (x0 < x1 ? 1 : -1), sy = (y0 < y1 ? 1 : -1);
		Integer err = dx - dy;
		
		while (x0 != x1 || y0 != y1) {
			if(proxy.getCell(x0, y0).isOccupied())
				return true;
			Integer e2 = 2 * err;
			if (e2 > -dy)	{err -= dy; x0 += sx;}
			if (e2 < dx)	{err += dx; y0 += sy;}
		}
		return false;
	}
}


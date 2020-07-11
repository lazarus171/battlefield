package battlefield;

import java.util.Comparator;

public class ComparaPosizione<T> implements Comparator<Robot> {
	
	public int compare(Robot a, Robot b) {
		int dX = a.getPosizione().getX()-b.getPosizione().getX();
		int dY = a.getPosizione().getY()-b.getPosizione().getY();
		if (dX != 0)
			return dX;
		return dY;
	}

}

package battlefield;


/*
 * Da sistemare. Vedi PositionTest.
 * (vedi DOMANDA 1)
 */
public class Position {
	
	private int x, y;

	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
		
	public String toString() {
		return this.x+"-"+this.y;
	}
	
	/* aggiunto per far funzionare PositionTest*/
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	/* aggiunto per far funzionare PositionTest*/
	public boolean equals(Object o) {
		Position obj = (Position) o;
		if(this.getX() == obj.getX() && this.getY() == obj.getY())
			return true;
		return false;
	}

}
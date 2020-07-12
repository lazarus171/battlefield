package battlefield;

/*Creata per rispondere alla domanda 2*/
public abstract class Robot implements Comparable<Robot>{
	
	private Position posizione;
	private int longevita;
	
	//Metodi rifattorizzati
	public Position getPosizione() {
		return this.posizione;
	}
	
	public int incrementaLongevita() {
		return ++this.longevita;
	}
	
	public int getLongevita() {
		return this.longevita;
	}
	
	//Metodi da implementare a cura delle sottoclassi
	public void passo(Battlefield field) {}
	
	public Position decidiMossa(Battlefield field){
		return new Position(0,0);
	}
	
	//Metodi aggiunti
	public void setPosizione(Position p) {
		this.posizione = p;
	}
	
	public void setLongevita(int l) {
		this.longevita = l;
	}
	
	/*Serve per la comparazione "interna", senza aggiungere altre classi*/
	public int compareTo(Robot o) {
		if(this.getClass() == o.getClass())
			return this.longevita - o.longevita;
		return -1;
	}

}

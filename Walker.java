package battlefield;

/*Modificata per rispondere alla domanda 2*/
public class Walker extends Robot {

	public Walker(Position p) {
		this.setPosizione(p);
		this.setLongevita(0);
	}

	public void passo(Battlefield field) {
		Position nuova = this.decidiMossa(field);
		if (nuova!=null) {
			Walker clone = new Walker(nuova);
			field.addWalker(clone);
		}
		this.incrementaLongevita();
	}
	
	public Position decidiMossa(Battlefield field) {
		Position corrente = this.getPosizione();
		Position libera = field.posizioneLiberaVicino(corrente);
		return libera; // verso una posizione libera
					   // tutto occupato: fermo
	}

}


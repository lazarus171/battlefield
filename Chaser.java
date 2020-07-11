package battlefield;

/*Modificata per rispondere alla domanda 2*/
public class Chaser extends Robot {
	
	public Chaser(Position p) {
		this.setPosizione(p);
		this.setLongevita(0) ;
	}
	
	public void passo(Battlefield field) {
		Position nuova = this.decidiMossa(field);
		if (nuova!=null) {
			Chaser clone = new Chaser(nuova);
			field.addChaser(clone);
		}
		this.incrementaLongevita();
	}
	
	public Position decidiMossa(Battlefield field) {
		Walker inseguito = cercaAvversario(field);
		if (inseguito==null) 
			return null; /* nessuno da inseguire: stai fermo */
		else return inseguito.getPosizione();
	}

	private Walker cercaAvversario(Battlefield field) {
		for(Position p : field.adiacenti(this.getPosizione())) {
			Robot vicino = field.getRobot(p);
			if (isAvversario(vicino)) {
				return (Walker) vicino;
			}
		}
		return null;
	}

	private boolean isAvversario(Object avvistato) {
		return (avvistato.getClass() == Walker.class); /* è sicuramente un Walker??? per ora SI! */
	}

}


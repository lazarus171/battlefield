package battlefield;

import java.util.*;

public class Battlefield {

	static final private Class<?>[] TIPOLOGIE = { Walker.class, Chaser.class } ;

	static final private int NUMERO_TIPOLOGIE = TIPOLOGIE.length;

	static final private double OCCUPAZIONE_INIZIALE = 0.01d; // 1%

	private int dim;

	private Random random;
	//Domanda 2
	//private Map<Position, Walker> posizione2walker;
	//private Map<Position, Chaser> posizione2chaser;
	private Map<Position, Robot> posizione2robot;

	public Battlefield(int dimensione) {
		this.dim = dimensione;
		//this.posizione2walker = new HashMap<>();
		//this.posizione2chaser = new HashMap<>();
		this.posizione2robot = new HashMap<>();
		this.random = new Random();
	}

	public void addWalker(Walker w) {
		// (vedi DOMANDA 1)
		//this.posizione2walker.put(w.getPosizione(), w);
		//Domanda 2
		this.posizione2robot.put(w.getPosizione(), w);
	}

	public void addChaser(Chaser c) {
		// (vedi DOMANDA 1)
		//this.posizione2chaser.put(c.getPosizione(), c);
		//Domanda 2
		this.posizione2robot.put(c.getPosizione(), c);
	}
	
	//Domanda 2
	/*
	public Walker getWalker(Position p) {
		return posizione2walker.get(p);
	}

	public Chaser getChaser(Position p) {
		return posizione2chaser.get(p);
	}*/
	
	public Robot getRobot(Position p) {
		return posizione2robot.get(p);
	}

	public Collection<Walker> getAllWalkers() {
		List<Walker> allWalkers = new LinkedList<Walker>();
		for(Robot r : this.posizione2robot.values()) {
			if(r.getClass().equals(Walker.class))
				allWalkers.add((Walker)r);
		}
		return allWalkers;
	}

	public Collection<Chaser> getAllChasers() {
		List<Chaser> allChasers = new LinkedList<Chaser>();
		for(Robot r : this.posizione2robot.values()) {
			if(r.getClass().equals(Chaser.class))
				allChasers.add((Chaser)r);
		}
		return allChasers;
	}

	@SuppressWarnings("rawtypes")
	public Map<Class, Set<Robot>> raggruppaRobotPerTipo() {
		// (vedi DOMANDA 3)
		Map<Class, Set<Robot>> mappa= new HashMap<Class, Set<Robot>>();
		Set<Robot> tmp;
		for(int i = 0; i < this.dim; i++) {
			for(int j = 0; j < this.dim; j++) {
				Robot r = this.getRobot(new Position(i,j));
				tmp = mappa.get(r.getClass());
				if(tmp == null) {
					tmp = new HashSet<Robot>();
				}
				tmp.add(r);
				mappa.put(r.getClass(), tmp);
			}
		}
		return mappa;
	}
	
	public List<Robot> getRobotOrdinatiPerPosizione() {
		// (vedi DOMANDA 4)
		List<Robot> lista = new LinkedList<Robot>();
		lista.addAll(getAllChasers());
		lista.addAll(getAllWalkers());
		ComparaPosizione<Robot> c = new ComparaPosizione<Robot>();
		Collections.sort(lista, c);
		return lista;
	}
	
	public SortedSet<?> getRobotOrdinatiPerLongevita() {
		// (vedi DOMANDA 6)
		return null;
	}
	
	public List<Position> adiacenti(Position perno) {
		final List<Position> adiacenti = new LinkedList<>();
		int x = perno.getX();
		int y = perno.getY();

		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				Position adiacente = new Position(x+i, y+j);
				if (inCampo(adiacente))
					adiacenti.add(adiacente);
			}
		}
		adiacenti.remove(perno);  // coincidono: quindi non sono adiacenti
		
		Collections.shuffle(adiacenti); /* ordine casuale */
		return adiacenti;
	}

	private boolean inCampo(Position p) {
		return  p.getX()>=0 && p.getX()<this.dim && 
				p.getY()>=0 && p.getY()<this.dim  ;
				
	} 

	public Position posizioneLiberaVicino(Position posizione) {
		for(Position p : this.adiacenti(posizione)) {
			if (this.isLibera(p)) {
				return p;
			}
		}
		return null;
	}

	public boolean isLibera(Position posizione) {
		return ( this.getRobot(posizione) == null);
	}

	public int getDimensione() {
		return this.dim;
	}

	public void riempi() {
		long numeroIniziale = Math.round(OCCUPAZIONE_INIZIALE * dim * dim);
		for(int i=0 ; i<numeroIniziale; i++) {
			int x = this.random.nextInt(this.dim);
			int y = this.random.nextInt(this.dim);
			Position posizione = new Position(x, y);
			if (this.isLibera(posizione)) {
				switch (this.random.nextInt(NUMERO_TIPOLOGIE)) {
				case 0: Chaser chaser = new Chaser(posizione);
						this.addChaser(chaser);
				break;
				case 1: Walker walker = new Walker(posizione);
						this.addWalker(walker);
				break;
				//case: NUMERO_TIPOLOGIE-1...
				default: throw new IllegalStateException();
				} 
			}
		}
	}

}

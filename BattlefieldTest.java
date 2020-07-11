package battlefield;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/* Modificare la classe Position affinche' 
 * il primo test abbia successo (vedi DOMANDA 1) 
 */
public class BattlefieldTest {
	
	private Battlefield field;
	
	@Before
	public void setUp() throws Exception {
		this.field = new Battlefield(2);
	}

	@Test
	public void testAddWalker() {
		assertEquals(0, this.field.getAllWalkers().size());
		this.field.addWalker(new Walker(new Position(0,0)));
		assertEquals(1, this.field.getAllWalkers().size());
	}
	
	@Test
	public void testRaggruppaRobotDiDueTipiDiversi() {
		//fail("vedi DOMANDA 3");
		assertEquals(0, this.field.getAllWalkers().size());
		assertEquals(0, this.field.getAllChasers().size());
		
		this.field.addChaser(new Chaser(new Position(0,1)));
		this.field.addChaser(new Chaser(new Position(1,0)));
		this.field.addWalker(new Walker(new Position(1,1)));
		this.field.addWalker(new Walker(new Position(0,0)));
		
		Map<Class, Set<Robot>> mappaTest = this.field.raggruppaRobotPerTipo();
		assertEquals(2, mappaTest.get(Walker.class).size());
		assertEquals(2, mappaTest.get(Chaser.class).size());
	}

}

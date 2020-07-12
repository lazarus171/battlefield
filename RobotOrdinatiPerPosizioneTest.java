package battlefield;

import static org.junit.Assert.*;

import org.junit.*;
import java.util.List;

public class RobotOrdinatiPerPosizioneTest {
	
	// (vedi DOMANDA 5)
	// scrivere dei test-case minimali per il metodo
	// Battlefield.getRobotOrdinatiPerPosizione()
	private Battlefield field;
	
	@Before
	public void setUp() {
		this.field = new Battlefield(5);
	}
	
	@Test
	public void testOrdinatiPerPosizione_campoVuoto() {
		List<Robot> result = this.field.getRobotOrdinatiPerPosizione();
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testOrdinatiPerPosizione_UnSingoloRobot() {
		this.field.addChaser(new Chaser(new Position(0,0)));
		List<Robot> result = this.field.getRobotOrdinatiPerPosizione();
		assertFalse(result.isEmpty());
	}
	
	@Test
	public void testOrdinatiPerPosizione_DueRobotXdiversa() {
		this.field.addChaser(new Chaser(new Position(1,2)));
		this.field.addWalker(new Walker(new Position(3,4)));
		List<Robot> result = this.field.getRobotOrdinatiPerPosizione();
		assertTrue(result.size() == 2);
		Robot r1 = result.get(0);
		Robot r2 = result.get(result.size()-1);
		assertEquals(1, r1.getPosizione().getX());
		assertEquals(3, r2.getPosizione().getX());
	}
	
	@Test
	public void testOrdinatiPerPosizione_DueRobotXugualeYdiversa() {
		this.field.addChaser(new Chaser(new Position(1,2)));
		this.field.addWalker(new Walker(new Position(1,4)));
		List<Robot> result = this.field.getRobotOrdinatiPerPosizione();
		assertTrue(result.size() == 2);
		Robot r1 = result.get(0);
		Robot r2 = result.get(result.size()-1);
		assertEquals(2, r1.getPosizione().getY());
		assertEquals(4, r2.getPosizione().getY());
	}
	
	@Test
	public void testOrdinatiPerPosizione_QuattroRobot() {
		this.field.addChaser(new Chaser(new Position(1,2)));
		this.field.addWalker(new Walker(new Position(3,4)));
		this.field.addChaser(new Chaser(new Position(1,3)));
		this.field.addWalker(new Walker(new Position(3,0)));
		List<Robot> result = this.field.getRobotOrdinatiPerPosizione();
		assertTrue(result.size() == 4);
		Robot r1 = result.get(0);
		Robot r2 = result.get(result.size()-1);
		assertEquals(1, r1.getPosizione().getX());
		assertEquals(2, r1.getPosizione().getY());
		assertEquals(3, r2.getPosizione().getX());
		assertEquals(4, r2.getPosizione().getY());
	}
	
}

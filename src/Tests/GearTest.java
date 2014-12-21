package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Gear;
import Model.Gear.GearStatus;

public class GearTest {

	private Gear g;
	@Before
	public void setUp() throws Exception {
		g = new Gear(GearStatus.down);
	}

	@After
	public void tearDown() throws Exception {
		g=null;
	}
	
	@Test
	public void testSetStatus() {
		g.setStatus(GearStatus.down);
		assertEquals(GearStatus.down,g.getStatus());
	}
	
	@Test
	public void testManeuver() {
		assertEquals(GearStatus.down,g.maneuver(GearStatus.down));
	}

}

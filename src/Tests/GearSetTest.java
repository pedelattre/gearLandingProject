package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Gear;
import Model.Gear.GearStatus;
import Model.GearSet;
import Model.Sensors;

public class GearSetTest {

	GearSet g;
	@Before
	public void setUp() throws Exception {
		g = new GearSet();
	}

	@After
	public void tearDown() throws Exception {
		g=null;
	}

	@Test
	public void testSetSensors() {
		Sensors s = new Sensors();
		g.setSensors(s);
		assertEquals(s, g.getSensors());
	}

	@Test
	public void testIsDownable() {
		g.getSensors().setForce(Sensors.planeWeight);
		g.getSensors().setSpeed(800);
		assertFalse(g.isDownable());
		g.getSensors().setForce(Sensors.planeWeight);
		g.getSensors().setSpeed(700);
		assertFalse(g.isDownable());
		g.getSensors().setForce(0);
		g.getSensors().setSpeed(800);
		assertFalse(g.isDownable());
		g.getSensors().setForce(0);
		g.getSensors().setSpeed(700);
		assertTrue(g.isDownable());
	}

	@Test
	public void testSetGearSetStatus() {
		g.setGearSetStatus(GearStatus.down);
		for (Gear gear : g.gearSet) {
			assertTrue(gear.getStatus() == GearStatus.down);
		}
	}

}

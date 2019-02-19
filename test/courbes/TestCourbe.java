package courbes;

import static org.junit.Assert.assertEquals;
import marche.donnees.courbes.Distance;
import marche.donnees.courbes.Temps;

import org.junit.Test;

public class TestCourbe {

	@Test
	public void testTAcc() {
		assertEquals(120, Temps.tAcc(100), 0);
	}

	@Test
	public void testDeltaTAcc() {
		assertEquals(120, Temps.acceleration(50, 150), 0);
	}

	@Test
	public void testDeltaTDec() {
		assertEquals(120, Temps.freinage(160, 40), 0);
	}

	@Test
	public void testDAcc() {
		assertEquals(1500, Distance.dAcc(50), 0);
		assertEquals(6000, Distance.dAcc(100), 0);
		assertEquals(13500, Distance.dAcc(150), 0);
		assertEquals(24000, Distance.dAcc(200), 0);
	}

	@Test
	public void testDeltaDAcc() {
		assertEquals(12000, Distance.acceleration(50, 150), 0);
	}

	@Test
	public void testDeltaDDec() {
		assertEquals(15000, Distance.freinage(200, 100), 0);
	}
}

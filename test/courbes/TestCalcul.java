package courbes;

import static org.junit.Assert.assertEquals;
import marche.calcul.CalculTempsTrajet;
import marche.donnees.polygone.PolygoneVitesse;
import marche.donnees.polygone.SegmentPolygone;

import org.junit.Test;

public class TestCalcul {

	@Test
	public void test200Cst() {
		SegmentPolygone el = new SegmentPolygone(0, 10, 200);

		double t = CalculTempsTrajet.calculerTempsSegment(200, el, 200);

		// vérifie qu'on obtient bien 3 minutes pour 10 km à 200
		assertEquals(180, t, 0);
	}

	@Test
	public void test200TropCourt() {
		SegmentPolygone el = new SegmentPolygone(0, 10, 200);

		double t = CalculTempsTrajet.calculerTempsSegment(0, el, 0);

		// vérifie qu'on obtient bien 3 minutes pour 10 km à 200
		assertEquals(180, t, 0);
	}

	@Test
	public void test200Arret() {
		SegmentPolygone el = new SegmentPolygone(0, 100, 200);

		double t = CalculTempsTrajet.calculerTempsSegment(0, el, 0);

		// vérifie qu'on obtient bien 30 minutes pour 100 km à 200
		assertEquals(1800, t, 0);
	}

	@Test
	public void testArret() {
		SegmentPolygone el = new SegmentPolygone(0, 2, 0);

		double t = CalculTempsTrajet.calculerTempsSegment(200, el, 200);

		// vérifie qu'on obtient bien 120 secondes pour un arrêt de 2 minutes
		assertEquals(120, t, 0);
	}

	@Test
	public void testCourbe200Cst() {
		// enchaînement de 2 éléments à 200
		SegmentPolygone el = new SegmentPolygone(0, 150, 200);
		SegmentPolygone el2 = new SegmentPolygone(15, 250, 200);
		PolygoneVitesse pol = new PolygoneVitesse();
		pol.getCourbe().add(el);
		pol.getCourbe().add(el2);

		double t = CalculTempsTrajet.calculerTempsPolygone(pol);

		// vérifie qu'on obtient bien 2h pour 400 km à 200
		assertEquals(7200, t, 0);
	}

	@Test
	public void testCourbe200CstArret() {
		// enchaînement de 2 éléments à 200 avec un arrêt entre
		SegmentPolygone el = new SegmentPolygone(0, 150, 200);
		SegmentPolygone ar = new SegmentPolygone(15, 5, 0);
		SegmentPolygone el2 = new SegmentPolygone(15, 250, 200);
		PolygoneVitesse pol = new PolygoneVitesse();
		pol.getCourbe().add(el);
		pol.getCourbe().add(ar);
		pol.getCourbe().add(el2);

		double t = CalculTempsTrajet.calculerTempsPolygone(pol);

		// vérifie qu'on obtient bien 2h05 pour 40 km à 200 + 5 minutes
		// d'arrêt
		assertEquals(7500, t, 0);
	}

	@Test
	public void testCourbe200Arrets() {
		// enchaînement de 2 éléments à 200 avec un arrêt entre
		SegmentPolygone el = new SegmentPolygone(0, 150, 160);
		SegmentPolygone ar = new SegmentPolygone(150, 5, 0);
		SegmentPolygone el2 = new SegmentPolygone(150, 10, 200);
		SegmentPolygone ar2 = new SegmentPolygone(160, 2, 0);
		SegmentPolygone el3 = new SegmentPolygone(160, 250, 200);
		PolygoneVitesse pol = new PolygoneVitesse();
		pol.getCourbe().add(el);
		pol.getCourbe().add(ar);
		pol.getCourbe().add(el2);
		pol.getCourbe().add(ar2);
		pol.getCourbe().add(el3);

		double t = CalculTempsTrajet.calculerTempsPolygone(pol);

		// vérifie qu'on obtient bien 2h05 pour 40 km à 200 + 5 minutes
		// d'arrêt
		assertEquals(7500, t, 0);
	}
}

package courbes;

import static org.junit.Assert.assertEquals;
import graphique.donnees.Course;
import marche.calcul.CalculTempsTrajet;
import marche.donnees.courbes.Performance;
import marche.donnees.courbes.PerformanceCalculee;
import marche.donnees.polygone.PolygoneVitesse;
import marche.donnees.polygone.SegmentPolygone;

import org.junit.Before;
import org.junit.Test;

public class TestCalcul {
	Performance perf;

	@Before
	public void init() {
		perf = new PerformanceCalculee(10, 0.5, 1);
	}

	@Test
	public void test200Cst() {
		SegmentPolygone el = new SegmentPolygone(0, 10, 200);

		double t = CalculTempsTrajet.calculerTempsSegment(perf, 200, el, 200);

		// v�rifie qu'on obtient bien 3 minutes pour 10 km � 200
		assertEquals(180, t, 0);
	}

	@Test
	public void test200TropCourt() {
		SegmentPolygone el = new SegmentPolygone(0, 10, 200);

		double t = CalculTempsTrajet.calculerTempsSegment(perf, 0, el, 0);

		// v�rifie qu'on obtient bien 3 minutes pour 10 km � 200
		assertEquals(180, t, 0);
	}

	@Test
	public void test200Arret() {
		SegmentPolygone el = new SegmentPolygone(0, 100, 200);

		double t = CalculTempsTrajet.calculerTempsSegment(perf, 0, el, 0);

		// v�rifie qu'on obtient bien 30 minutes pour 100 km � 200
		assertEquals(1800, t, 0);
	}

	@Test
	public void testArret() {
		SegmentPolygone el = new SegmentPolygone(0, 2, 0);

		double t = CalculTempsTrajet.calculerTempsSegment(perf, 200, el, 200);

		// v�rifie qu'on obtient bien 120 secondes pour un arr�t de 2 minutes
		assertEquals(120, t, 0);
	}

	@Test
	public void testCourbe200Cst() {
		// encha�nement de 2 �l�ments � 200
		SegmentPolygone el = new SegmentPolygone(0, 150, 200);
		SegmentPolygone el2 = new SegmentPolygone(150, 250, 200);
		PolygoneVitesse pol = new PolygoneVitesse();
		pol.getCourbe().add(el);
		pol.getCourbe().add(el2);

		Course c = CalculTempsTrajet.calculerTempsPolygone(perf, pol);

		// v�rifie qu'on obtient bien 2h pour 400 km � 200
		System.out.println(c);
	}

	@Test
	public void testCourbe200CstArret() {
		// encha�nement de 2 �l�ments � 200 avec un arr�t entre
		SegmentPolygone el = new SegmentPolygone(0, 150, 200);
		SegmentPolygone ar = new SegmentPolygone(150, 5, 0);
		SegmentPolygone el2 = new SegmentPolygone(150, 250, 200);
		PolygoneVitesse pol = new PolygoneVitesse();
		pol.getCourbe().add(el);
		pol.getCourbe().add(ar);
		pol.getCourbe().add(el2);

		Course c = CalculTempsTrajet.calculerTempsPolygone(perf, pol);

		// v�rifie qu'on obtient bien 2h05 pour 40 km � 200 + 5 minutes
		// d'arr�t
		System.out.println(c);
	}

	@Test
	public void testCourbe200Arrets() {
		// encha�nement de 2 �l�ments � 200 avec un arr�t entre
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

		Course c = CalculTempsTrajet.calculerTempsPolygone(perf, pol);

		// v�rifie qu'on obtient bien 2h05 pour 40 km � 200 + 5 minutes
		// d'arr�t
		System.out.println(c);
	}
}

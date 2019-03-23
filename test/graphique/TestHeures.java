package graphique;

import graphique.donnees.Course;
import graphique.donnees.Heure;
import graphique.horaire.CalculHoraire;
import marche.donnees.courbes.Performance;
import marche.donnees.courbes.PerformanceCalculee;
import marche.donnees.polygone.PolygoneVitesse;
import marche.donnees.polygone.SegmentPolygone;

import org.junit.Before;
import org.junit.Test;

public class TestHeures {
	Performance perf;

	@Before
	public void init() {
		perf = new PerformanceCalculee(10, 0.5, 1);
	}

	@Test
	public void test() {
		Heure h = new Heure(36162);
		System.out.println(h);
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

		Heure dep = new Heure(17, 42);

		Course c = CalculHoraire.creerCourse(perf, pol, dep, 500, false);

		// vérifier qu'on obtient bien 2h05 pour 40 km à 200 + 5 minutes d'arrêt
		System.out.println(c);
	}
}

package graphique;

import graphique.donnees.Course;
import graphique.donnees.Heure;
import graphique.horaire.CalculHoraire;
import marche.donnees.polygone.PolygoneVitesse;
import marche.donnees.polygone.SegmentPolygone;

import org.junit.Test;

public class TestHeures {

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

		Course c = CalculHoraire.creerCourse(pol, dep, 500, false);

		// vérifier qu'on obtient bien 2h05 pour 40 km à 200 + 5 minutes d'arrêt
		System.out.println(c);
	}
}

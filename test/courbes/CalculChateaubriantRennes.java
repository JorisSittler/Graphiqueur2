package courbes;

import marche.calcul.CalculTempsTrajet;
import marche.donnees.courbes.Performance;
import marche.donnees.courbes.PerformanceCalculee;
import marche.donnees.polygone.PolygoneVitesse;
import marche.donnees.polygone.SegmentPolygone;

import org.junit.Before;
import org.junit.Test;

public class CalculChateaubriantRennes {
	Performance perf;

	@Before
	public void init() {
		// 7 kW/T, 0,7 m/s² max en accélération et 1m/s² en décélération
		perf = new PerformanceCalculee(7, 0.7, 1);
	}

	@Test
	public void testCalculTempsTrajetOptimal() {
		System.out.println("Calcul du trajet Châteaubriant-Rennes avec les performances de base :");

		PolygoneVitesse pol = new PolygoneVitesse();
		SegmentPolygone el = new SegmentPolygone(0, 14.9, 90);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(14.9, 0.5, 0);// arrêt Martigné
		pol.getCourbe().add(el);

		el = new SegmentPolygone(14.9, 11.7, 90);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(14.9, 0.5, 0);// arrêt Retiers
		pol.getCourbe().add(el);

		el = new SegmentPolygone(26.6, 3.3, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(29.9, 0.5, 0);// arrêt Le Theil
		pol.getCourbe().add(el);

		el = new SegmentPolygone(29.9, 6.2, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(36.1, 0.3, 30);// VL30 en approche du croisement
		pol.getCourbe().add(el);
		el = new SegmentPolygone(36.4, 2, 0);// arrêt croisement Janzé
		pol.getCourbe().add(el);

		el = new SegmentPolygone(36.4, 6.9, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(43.3, 0.5, 0);// arrêt Corps-Nuds
		pol.getCourbe().add(el);

		el = new SegmentPolygone(43.3, 3.5, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(46.8, 0.5, 0);// arrêt St Armel
		pol.getCourbe().add(el);

		el = new SegmentPolygone(46.8, 3.7, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(50.5, 0.5, 0);// arrêt Vern
		pol.getCourbe().add(el);

		el = new SegmentPolygone(50.5, 5.8, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(56.3, 0.5, 0);// arrêt La Poterie
		pol.getCourbe().add(el);

		el = new SegmentPolygone(56.3, 3.7, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(60, 0.3, 30);
		pol.getCourbe().add(el); // arrivée Rennes

		CalculTempsTrajet.calculerTempsPolygone(perf, pol);
	}

	@Test
	public void testCalculTempsTrajetJanzeRennes() {
		System.out.println("Calcul du trajet Janzé-Rennes avec les performances de base :");

		PolygoneVitesse pol = new PolygoneVitesse();
		SegmentPolygone el = new SegmentPolygone(36.4, 6.9, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(43.3, 0.5, 0);// arrêt Corps-Nuds
		pol.getCourbe().add(el);

		el = new SegmentPolygone(43.3, 3.5, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(46.8, 0.5, 0);// arrêt St Armel
		pol.getCourbe().add(el);

		el = new SegmentPolygone(46.8, 3.7, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(50.5, 0.5, 0);// arrêt Vern
		pol.getCourbe().add(el);

		el = new SegmentPolygone(50.5, 5.8, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(56.3, 0.5, 0);// arrêt La Poterie
		pol.getCourbe().add(el);

		el = new SegmentPolygone(56.3, 3.7, 110);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(60, 0.3, 30);
		pol.getCourbe().add(el); // arrivée Rennes

		CalculTempsTrajet.calculerTempsPolygone(perf, pol);
	}

	@Test
	public void testCalculTempsTrajetVLOrigine() {
		System.out.println("Calcul du trajet Châteaubriant-Rennes avec les VL d'origine (110 puis 90 km/h) :");

		PolygoneVitesse pol = new PolygoneVitesse();
		SegmentPolygone el = new SegmentPolygone(0, 14.9, 70);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(14.9, 0.5, 0);// arrêt Martigné
		pol.getCourbe().add(el);

		el = new SegmentPolygone(14.9, 11.7, 70);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(14.9, 0.5, 0);// arrêt Retiers
		pol.getCourbe().add(el);

		el = new SegmentPolygone(26.6, 3.3, 90);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(29.9, 0.5, 0);// arrêt Le Theil
		pol.getCourbe().add(el);

		el = new SegmentPolygone(29.9, 6.2, 90);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(36.1, 0.3, 30);// VL30 en approche du croisement
		pol.getCourbe().add(el);
		el = new SegmentPolygone(36.4, 2, 0);// arrêt croisement Janzé
		pol.getCourbe().add(el);

		el = new SegmentPolygone(36.4, 6.9, 90);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(43.3, 0.5, 0);// arrêt Corps-Nuds
		pol.getCourbe().add(el);

		el = new SegmentPolygone(43.3, 3.5, 90);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(46.8, 0.5, 0);// arrêt St Armel
		pol.getCourbe().add(el);

		el = new SegmentPolygone(46.8, 3.7, 90);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(50.5, 0.5, 0);// arrêt Vern
		pol.getCourbe().add(el);

		el = new SegmentPolygone(50.5, 5.8, 90);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(56.3, 0.5, 0);// arrêt La Poterie
		pol.getCourbe().add(el);

		el = new SegmentPolygone(56.3, 3.7, 90);
		pol.getCourbe().add(el);
		el = new SegmentPolygone(60, 0.3, 30);
		pol.getCourbe().add(el); // arrivée Rennes

		CalculTempsTrajet.calculerTempsPolygone(perf, pol);
	}

}
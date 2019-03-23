package courbes;

import marche.calcul.CalculTempsTrajet;
import marche.donnees.courbes.Performance;
import marche.donnees.courbes.PerformanceCalculee;
import marche.donnees.polygone.PolygoneVitesse;
import marche.donnees.polygone.SegmentPolygone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestCalculPerfTheoriques {
	Performance perf, perf2;

	@Before
	public void init() {
		perf = new PerformanceCalculee(10, 0.5, 1);
		perf2 = new PerformanceCalculee(20, 1, 1);
	}

	@Test
	public void testDec() {
		// freinage de 1m/s² depuis 30 m/s à 0 en 30s
		Assert.assertEquals(30, perf.tempsFreinage(108, 0), 0);

		// System.out.println(perf.distFreinage(108, 0));
	}

	@Ignore
	@Test
	public void testAcc() {
		System.out.println(perf.tempsAcceleration(0, 18));
		System.out.println(perf.tempsAcceleration(0, 36));
		System.out.println(perf.tempsAcceleration(0, 72));
		System.out.println(perf.tempsAcceleration(0, 144));

		System.out.println(perf.distAcceleration(0, 18));
		System.out.println(perf.distAcceleration(0, 36));
		System.out.println(perf.distAcceleration(0, 72));
		System.out.println(perf.distAcceleration(0, 144));
	}

	@Test
	public void testCalculTempsTrajet() {
		System.out.println("Calcul du trajet avec les performances de base :");

		SegmentPolygone el = new SegmentPolygone(0, 10, 160);
		PolygoneVitesse pol = new PolygoneVitesse();
		pol.getCourbe().add(el);

		CalculTempsTrajet.calculerTempsPolygone(perf, pol);

		System.out.println("Pour comparaison, calcul du même trajet avec des performances doubles en accélération :");

		CalculTempsTrajet.calculerTempsPolygone(perf2, pol);
	}

	@Test
	public void testCalculTempsTrajetVL100() {
		System.out
				.println("\n\nPour comparaison, calcul du trajet avec les performances de base mais vitesse max 100 :");

		SegmentPolygone el = new SegmentPolygone(0, 10, 100);
		PolygoneVitesse pol = new PolygoneVitesse();
		pol.getCourbe().add(el);

		CalculTempsTrajet.calculerTempsPolygone(perf, pol);

		System.out.println("Pour comparaison, calcul du même trajet avec des performances doubles en accélération :");

		CalculTempsTrajet.calculerTempsPolygone(perf2, pol);
	}
}

package graphique.horaire;

import graphique.donnees.Course;
import graphique.donnees.Heure;
import marche.calcul.CalculTempsTrajet;
import marche.donnees.courbes.Performance;
import marche.donnees.polygone.PolygoneVitesse;

public class CalculHoraire {
	public static Course creerCourse(Performance perf, PolygoneVitesse polygone, Heure heureDepart, double pkDepart,
			boolean sensImpair) {
		Course course = CalculTempsTrajet.calculerTempsPolygone(perf, polygone);

		course.retarderDe(heureDepart);
		course.decalerPK(pkDepart, !sensImpair);

		return course;
	}
}

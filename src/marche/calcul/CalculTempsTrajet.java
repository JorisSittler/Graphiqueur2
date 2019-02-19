package marche.calcul;

import marche.donnees.courbes.Distance;
import marche.donnees.courbes.Temps;
import marche.donnees.polygone.PolygoneVitesse;
import marche.donnees.polygone.SegmentPolygone;

public class CalculTempsTrajet {

	public static double calculerTempsPolygone(PolygoneVitesse poly) {
		double tempsTotal = 0;

		for (int i = 0; i < poly.getCourbe().size(); i++) {
			SegmentPolygone segment = poly.getCourbe().get(i);

			// Les vitesses initiales et finales sont 0 par défaut au début et à
			// la fin de la courbe
			double vDebut = 0, vFin = 0;

			// si on n'est pas au début on prend la vitesse du segment précédent
			if (i > 0) {
				vDebut = poly.getCourbe().get(i - 1).getVitessePlafond();
			}
			// si on n'est pas au dernier segment, on prend comme vitesse de fin
			// celle du segment suivant
			if (i < poly.getCourbe().size() - 1) {
				vFin = poly.getCourbe().get(i + 1).getVitessePlafond();
			}
			double tempsSegment = calculerTempsSegment(vDebut, segment, vFin);
			tempsTotal += tempsSegment;

			if (segment.getVitessePlafond() > 0) {
				System.out.println("km " + segment.getPkDebut() + " : Début d'un segment de " + segment.getLongueur()
						+ " km à " + segment.getVitessePlafond() + " km/h, durée : " + tempsSegment
						+ " sec ; temps écoulé depuis départ : " + tempsTotal + " sec");
			} else {
				System.out.println("km " + segment.getPkDebut() + " : Début d'un arrêt de " + segment.getLongueur()
						+ " minutes, durée : " + tempsSegment + " sec ; temps écoulé depuis départ : " + tempsTotal
						+ " sec");
			}

		}
		System.out.println("arrivé au bout de " + tempsTotal + " secondes");
		return tempsTotal;
	}

	/**
	 * Calcule le temps de trajet en secondes sur un élément du parcours
	 * 
	 * @param vitesseAvant
	 * @param element
	 * @param vitesseApres
	 * @return la durée en secondes
	 */
	public static double calculerTempsSegment(double vitesseAvant, SegmentPolygone element, double vitesseApres) {
		double vitessePlafond = element.getVitessePlafond();
		if (vitessePlafond > 0) {
			// si ce n'est pas un arrêt
			double distanceSegment = element.getLongueur();
			double distanceAcc = 0, distanceDec = 0;
			double tempsAcc = 0, tempsDec = 0;

			if (vitessePlafond > vitesseAvant) {
				// accélération au début
				distanceAcc = Distance.acceleration(vitesseAvant, vitessePlafond);
				tempsAcc = Temps.acceleration(vitesseAvant, vitessePlafond);
				System.out.println("\taccélération en début de segment de " + vitesseAvant + " à " + vitessePlafond
						+ " km/h : " + distanceAcc / 1000 + " km parcourus en " + tempsAcc + " sec");
			}
			if (vitessePlafond > vitesseApres) {
				// décélération à la fin
				distanceDec = Distance.freinage(vitessePlafond, vitesseApres);
				tempsDec = Temps.freinage(vitessePlafond, vitesseApres);
				System.out.println("\tdécélération en fin de segment de " + vitessePlafond + " à " + vitesseApres
						+ " km/h : " + distanceDec / 1000 + " km parcourus en " + tempsDec + " sec");
			}
			// distance en m restant à parcourir à Vmax
			double distanceVCst = distanceSegment * 1000 - (distanceAcc + distanceDec);

			// si résultat négatif : pas le temps d'atteindre Vplafond donc on va réessayer de façon récursive avec une
			// vitesse plus faible de 5% jusqu'à atteindre un cas passant
			if (distanceVCst < 0) {
				System.out.println("\t\tdistance trop courte, on réessaye avec une vitesse réduite à " + vitessePlafond
						* 0.95);
				return calculerTempsSegment(vitesseAvant, new SegmentPolygone(element, vitessePlafond * 0.95),
						vitesseApres);
			}

			double tempsVCst = distanceVCst / vitessePlafond * 3.6;
			System.out.println("\til reste" + distanceVCst / 1000 + " km à parcourir à vitesse constante en "
					+ tempsVCst + " sec");

			// on cumule les trois temps :
			return tempsAcc + tempsVCst + tempsDec;
		} else {
			// si c'est un arrêt on renvoie simplement sa durée
			return element.getLongueur() * 60;
		}
	}
}

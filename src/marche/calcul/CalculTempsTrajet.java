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

			// Les vitesses initiales et finales sont 0 par d�faut au d�but et �
			// la fin de la courbe
			double vDebut = 0, vFin = 0;

			// si on n'est pas au d�but on prend la vitesse du segment pr�c�dent
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
				System.out.println("km " + segment.getPkDebut() + " : D�but d'un segment de " + segment.getLongueur()
						+ " km � " + segment.getVitessePlafond() + " km/h, dur�e : " + tempsSegment
						+ " sec ; temps �coul� depuis d�part : " + tempsTotal + " sec");
			} else {
				System.out.println("km " + segment.getPkDebut() + " : D�but d'un arr�t de " + segment.getLongueur()
						+ " minutes, dur�e : " + tempsSegment + " sec ; temps �coul� depuis d�part : " + tempsTotal
						+ " sec");
			}

		}
		System.out.println("arriv� au bout de " + tempsTotal + " secondes");
		return tempsTotal;
	}

	/**
	 * Calcule le temps de trajet en secondes sur un �l�ment du parcours
	 * 
	 * @param vitesseAvant
	 * @param element
	 * @param vitesseApres
	 * @return la dur�e en secondes
	 */
	public static double calculerTempsSegment(double vitesseAvant, SegmentPolygone element, double vitesseApres) {
		double vitessePlafond = element.getVitessePlafond();
		if (vitessePlafond > 0) {
			// si ce n'est pas un arr�t
			double distanceSegment = element.getLongueur();
			double distanceAcc = 0, distanceDec = 0;
			double tempsAcc = 0, tempsDec = 0;

			if (vitessePlafond > vitesseAvant) {
				// acc�l�ration au d�but
				distanceAcc = Distance.acceleration(vitesseAvant, vitessePlafond);
				tempsAcc = Temps.acceleration(vitesseAvant, vitessePlafond);
				System.out.println("\tacc�l�ration en d�but de segment de " + vitesseAvant + " � " + vitessePlafond
						+ " km/h : " + distanceAcc / 1000 + " km parcourus en " + tempsAcc + " sec");
			}
			if (vitessePlafond > vitesseApres) {
				// d�c�l�ration � la fin
				distanceDec = Distance.freinage(vitessePlafond, vitesseApres);
				tempsDec = Temps.freinage(vitessePlafond, vitesseApres);
				System.out.println("\td�c�l�ration en fin de segment de " + vitessePlafond + " � " + vitesseApres
						+ " km/h : " + distanceDec / 1000 + " km parcourus en " + tempsDec + " sec");
			}
			// distance en m restant � parcourir � Vmax
			double distanceVCst = distanceSegment * 1000 - (distanceAcc + distanceDec);

			// si r�sultat n�gatif : pas le temps d'atteindre Vplafond donc on va r�essayer de fa�on r�cursive avec une
			// vitesse plus faible de 5% jusqu'� atteindre un cas passant
			if (distanceVCst < 0) {
				System.out.println("\t\tdistance trop courte, on r�essaye avec une vitesse r�duite � " + vitessePlafond
						* 0.95);
				return calculerTempsSegment(vitesseAvant, new SegmentPolygone(element, vitessePlafond * 0.95),
						vitesseApres);
			}

			double tempsVCst = distanceVCst / vitessePlafond * 3.6;
			System.out.println("\til reste" + distanceVCst / 1000 + " km � parcourir � vitesse constante en "
					+ tempsVCst + " sec");

			// on cumule les trois temps :
			return tempsAcc + tempsVCst + tempsDec;
		} else {
			// si c'est un arr�t on renvoie simplement sa dur�e
			return element.getLongueur() * 60;
		}
	}
}

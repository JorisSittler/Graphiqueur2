package marche.donnees.courbes;

public class Temps {
	/**
	 * @param vitesseA
	 *            vitesse de début de phase
	 * @param vitesseB
	 *            vitesse de fin de phase
	 * @return le temps passé à accélérer entre la vitesse a et la vitesse b
	 */
	public static double acceleration(double vitesseA, double vitesseB) {
		double tA = tAcc(vitesseA);
		double tB = tAcc(vitesseB);
		return tB - tA;
	}

	/**
	 * 
	 * @param vitesse
	 *            la vitesse
	 * @return le temps en secondes entre le démarrage et l'atteinte de la
	 *         vitesse v
	 */
	public static double tAcc(double vitesse) {
		// TODO courbe réaliste
		return vitesse * 1.2;
	}

	/**
	 * 
	 * @param vitessePlafond
	 *            vitesse de début de phase
	 * @param vitesseApres
	 *            vitesse de fin de phase
	 * @return le temps passé à accélérer entre la vitesse a et la vitesse b
	 */
	public static double freinage(double vitessePlafond, double vitesseApres) {
		double tA = tDec(vitessePlafond);
		double tB = tDec(vitesseApres);
		return tA - tB;
	}

	/**
	 * 
	 * @param vitesse
	 *            la vitesse
	 * @return le temps en secondes entre le début du freinage en vitesse et
	 *         l'arrêt
	 */
	public static double tDec(double vitesse) {
		// TODO courbe réaliste
		return vitesse;
	}
}

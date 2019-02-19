package marche.donnees.courbes;

public class Temps {
	/**
	 * @param vitesseA
	 *            vitesse de d�but de phase
	 * @param vitesseB
	 *            vitesse de fin de phase
	 * @return le temps pass� � acc�l�rer entre la vitesse a et la vitesse b
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
	 * @return le temps en secondes entre le d�marrage et l'atteinte de la
	 *         vitesse v
	 */
	public static double tAcc(double vitesse) {
		// TODO courbe r�aliste
		return vitesse * 1.2;
	}

	/**
	 * 
	 * @param vitessePlafond
	 *            vitesse de d�but de phase
	 * @param vitesseApres
	 *            vitesse de fin de phase
	 * @return le temps pass� � acc�l�rer entre la vitesse a et la vitesse b
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
	 * @return le temps en secondes entre le d�but du freinage en vitesse et
	 *         l'arr�t
	 */
	public static double tDec(double vitesse) {
		// TODO courbe r�aliste
		return vitesse;
	}
}

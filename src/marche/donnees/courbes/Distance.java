package marche.donnees.courbes;

public class Distance {
	/**
	 * @param vitesseAvant
	 *            vitesse de début de phase
	 * @param vitessePlafond
	 *            vitesse de fin de phase
	 * @return la distance parcourue en m à accélérer entre la vitesse a et la
	 *         vitesse b
	 */
	public static double acceleration(double vitesseAvant, double vitessePlafond) {
		double dA = dAcc(vitesseAvant);
		double dB = dAcc(vitessePlafond);
		return dB - dA;
	}

	/**
	 * 
	 * @param vitesse
	 *            la vitesse
	 * @return la distance en m entre le démarrage et l'atteinte de la vitesse v
	 */
	public static double dAcc(double vitesse) {
		// TODO courbe réaliste
		return Math.sqrt(vitesse) * 500;
	}

	/**
	 * 
	 * @param vitesseA
	 *            vitesse de début de phase
	 * @param vitesseB
	 *            vitesse de fin de phase
	 * @return la distance parcourue en m à accélérer entre la vitesse a et la
	 *         vitesse b
	 */
	public static double freinage(double vitesseA, double vitesseB) {
		double dA = dDec(vitesseA);
		double dB = dDec(vitesseB);
		return dA - dB;
	}

	/**
	 * 
	 * @param vitesse
	 *            la vitesse
	 * @return la distance parcourue en m entre le début du freinage en vitesse
	 *         et l'arrêt
	 */
	public static double dDec(double vitesse) {
		// TODO courbe réaliste
		return Math.sqrt(vitesse) * 400;
	}
}

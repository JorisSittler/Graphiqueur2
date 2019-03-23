package marche.donnees.courbes;

public abstract class Performance {

	/**
	 * @param vitesseAvant
	 *            vitesse de début de phase
	 * @param vitessePlafond
	 *            vitesse de fin de phase
	 * @return la distance parcourue en m à accélérer entre la vitesse a et la vitesse b
	 */
	public double distAcceleration(double vitesseAvant, double vitessePlafond) {
		double dA = dAcc(vitesseAvant);
		double dB = dAcc(vitessePlafond);
		return dB - dA;
	}

	/**
	 * @param vitesse
	 *            la vitesse
	 * @return la distance en m entre le démarrage et l'atteinte de la vitesse v
	 */
	protected abstract double dAcc(double vitesse);

	/**
	 * @param vitesseA
	 *            vitesse de début de phase
	 * @param vitesseB
	 *            vitesse de fin de phase
	 * @return la distance parcourue en m à accélérer entre la vitesse a et la vitesse b
	 */
	public double distFreinage(double vitesseA, double vitesseB) {
		double dA = dDec(vitesseA);
		double dB = dDec(vitesseB);
		return dA - dB;
	}

	/**
	 * @param vitesse
	 *            la vitesse
	 * @return la distance parcourue en m entre le début du freinage en vitesse et l'arrêt
	 */
	protected abstract double dDec(double vitesse);

	/**
	 * @param vitesseA
	 *            vitesse de début de phase
	 * @param vitesseB
	 *            vitesse de fin de phase
	 * @return le temps passé à accélérer entre la vitesse a et la vitesse b
	 */
	public double tempsAcceleration(double vitesseA, double vitesseB) {
		double tA = tAcc(vitesseA);
		double tB = tAcc(vitesseB);
		return tB - tA;
	}

	/**
	 * @param vitesse
	 *            la vitesse
	 * @return le temps en secondes entre le démarrage et l'atteinte de la vitesse v
	 */
	protected abstract double tAcc(double vitesse);

	/**
	 * @param vitessePlafond
	 *            vitesse de début de phase
	 * @param vitesseApres
	 *            vitesse de fin de phase
	 * @return le temps passé à accélérer entre la vitesse a et la vitesse b
	 */
	public double tempsFreinage(double vitessePlafond, double vitesseApres) {
		double tA = tDec(vitessePlafond);
		double tB = tDec(vitesseApres);
		return tA - tB;
	}

	/**
	 * @param vitesse
	 *            la vitesse
	 * @return le temps en secondes entre le début du freinage en vitesse et l'arrêt
	 */
	protected abstract double tDec(double vitesse);

}

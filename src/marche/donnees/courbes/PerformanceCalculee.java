package marche.donnees.courbes;

public class PerformanceCalculee extends Performance {
	protected double puissanceMassique;
	protected double accMax;
	/* décélération supposée constante */
	protected double dec;

	/**
	 * en-dessous de cette vitesse l'accélération est plafonnée pour confort et/ou adhérence <br/>
	 * au-dessus c'est la puissance qui limite l'accélération
	 */
	protected double vitessePMax() {
		return 3.6 * puissanceMassique / accMax;
	}

	/**
	 * Paramétrage des performances
	 * 
	 * @param Pm
	 *            puissance massique en kW/T (ou W/kg)
	 * @param acc
	 *            accélération limite en m/s²
	 * @param dec
	 *            décélération (constante) en m/s²
	 */
	public PerformanceCalculee(double Pm, double acc, double dec) {
		puissanceMassique = Pm;
		accMax = acc;
		this.dec = dec;
	}

	@Override
	public double dAcc(double vitesse) {
		if (vitesse <= vitessePMax()) {
			// on est uniquement dans la phase d'accélération constante
			return vitesse * vitesse / (2 * accMax * 12.96);
		} else {
			// sinon on a d'abord le temps jusqu'à vitessePMAx
			double disJusqueVPmax = vitessePMax() * vitessePMax() / (2 * accMax * 12.96);

			// auquel on ajoute le temps sur la partie à puissance constante, où l'accélération est inversement
			// proportionnelle à la vitesse
			double distanceApresVPMax = (vitesse * vitesse * vitesse - vitessePMax() * vitessePMax() * vitessePMax())
					/ (3 * puissanceMassique * 46.656);

			return disJusqueVPmax + distanceApresVPMax;
		}
	}

	@Override
	public double dDec(double vitesse) {
		// on est uniquement dans la phase d'accélération constante
		return vitesse * vitesse / (2 * dec * 12.96);
	}

	@Override
	public double tAcc(double vitesse) {
		if (vitesse <= vitessePMax()) {
			// on est uniquement dans la phase d'accélération constante
			return vitesse / (3.6 * accMax);
		} else {
			// sinon on a d'abord le temps jusqu'à vitessePMAx
			double tempsJusqueVPMax = vitessePMax() / (3.6 * accMax);

			// auquel on ajoute le temps sur la partie à puissance constante, où l'accélération est inversement
			// proportionnelle à la vitesse
			double tempsApresVPMax = (vitesse * vitesse - vitessePMax() * vitessePMax())
					/ (2 * puissanceMassique * 12.96);

			return tempsJusqueVPMax + tempsApresVPMax;
		}
	}

	@Override
	public double tDec(double vitesse) {
		// on est uniquement dans la phase d'accélération constante
		return vitesse / (3.6 * dec);
	}
}

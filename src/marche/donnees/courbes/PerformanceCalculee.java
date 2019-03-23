package marche.donnees.courbes;

public class PerformanceCalculee extends Performance {
	protected double puissanceMassique;
	protected double accMax;
	/* d�c�l�ration suppos�e constante */
	protected double dec;

	/**
	 * en-dessous de cette vitesse l'acc�l�ration est plafonn�e pour confort et/ou adh�rence <br/>
	 * au-dessus c'est la puissance qui limite l'acc�l�ration
	 */
	protected double vitessePMax() {
		return 3.6 * puissanceMassique / accMax;
	}

	/**
	 * Param�trage des performances
	 * 
	 * @param Pm
	 *            puissance massique en kW/T (ou W/kg)
	 * @param acc
	 *            acc�l�ration limite en m/s�
	 * @param dec
	 *            d�c�l�ration (constante) en m/s�
	 */
	public PerformanceCalculee(double Pm, double acc, double dec) {
		puissanceMassique = Pm;
		accMax = acc;
		this.dec = dec;
	}

	@Override
	public double dAcc(double vitesse) {
		if (vitesse <= vitessePMax()) {
			// on est uniquement dans la phase d'acc�l�ration constante
			return vitesse * vitesse / (2 * accMax * 12.96);
		} else {
			// sinon on a d'abord le temps jusqu'� vitessePMAx
			double disJusqueVPmax = vitessePMax() * vitessePMax() / (2 * accMax * 12.96);

			// auquel on ajoute le temps sur la partie � puissance constante, o� l'acc�l�ration est inversement
			// proportionnelle � la vitesse
			double distanceApresVPMax = (vitesse * vitesse * vitesse - vitessePMax() * vitessePMax() * vitessePMax())
					/ (3 * puissanceMassique * 46.656);

			return disJusqueVPmax + distanceApresVPMax;
		}
	}

	@Override
	public double dDec(double vitesse) {
		// on est uniquement dans la phase d'acc�l�ration constante
		return vitesse * vitesse / (2 * dec * 12.96);
	}

	@Override
	public double tAcc(double vitesse) {
		if (vitesse <= vitessePMax()) {
			// on est uniquement dans la phase d'acc�l�ration constante
			return vitesse / (3.6 * accMax);
		} else {
			// sinon on a d'abord le temps jusqu'� vitessePMAx
			double tempsJusqueVPMax = vitessePMax() / (3.6 * accMax);

			// auquel on ajoute le temps sur la partie � puissance constante, o� l'acc�l�ration est inversement
			// proportionnelle � la vitesse
			double tempsApresVPMax = (vitesse * vitesse - vitessePMax() * vitessePMax())
					/ (2 * puissanceMassique * 12.96);

			return tempsJusqueVPMax + tempsApresVPMax;
		}
	}

	@Override
	public double tDec(double vitesse) {
		// on est uniquement dans la phase d'acc�l�ration constante
		return vitesse / (3.6 * dec);
	}
}

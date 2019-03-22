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

	@Override
	public double dAcc(double vitesse) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double dDec(double vitesse) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double tAcc(double vitesse) {
		if (vitesse <= vitessePMax()) {
			// on est uniquement dans la phase d'acc�l�ration constante
			return vitesse / (3.6 * accMax);
		} else {
			// sinon on a d'abord le temps jusqu'� vitessePMAx
			double tempsJusqueVPMax = vitessePMax() / (3.6 * accMax);

			// auquel on ajoute le temps sur la partie � puissance constante, o� l'acc�l�ration est hyperbolo�de
			// TODO
		}
		return 0;
	}

	@Override
	public double tDec(double vitesse) {
		// on est uniquement dans la phase d'acc�l�ration constante
		return vitesse / (3.6 * dec);
	}
}

package graphique.donnees;

public class PointDePassage {
	Heure heure;
	double pk;

	/**
	 * 
	 * @param h
	 *            l'Heure
	 * @param k
	 *            le pk
	 */
	public PointDePassage(Heure h, double k) {
		heure = h;
		pk = k;
	}

	public PointDePassage(String[] tab) {
		heure = new Heure(Integer.parseInt(tab[0]), Float.parseFloat(tab[1]));
		pk = Float.parseFloat(tab[2]);
	}

	public void retarderDe(float m) {
		heure = heure.retarderDe(m);
	}

	public void retarderDe(Heure h) {
		heure = heure.plus(h);
	}

	@Override
	public String toString() {
		return (heure + ", " + pk);
	}

	public String toCSV() {
		return ("" + heure.toCSV() + ", " + pk);
	}
}

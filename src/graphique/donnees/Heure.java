package graphique.donnees;

public class Heure {
	int heure;
	double minute;

	public Heure(int h, double m) {
		heure = h;
		minute = m;
	}

	/**
	 * 
	 * @param secondes
	 *            l'heure en secondes qui sera convertie
	 */
	public Heure(double secondes) {
		heure = (int) (secondes / 3600);
		minute = (secondes % 3600) / 60;
	}

	public Heure plus(Heure a) {
		int h;
		double m;

		m = this.minute + a.minute;
		h = this.heure + a.heure;
		while (m >= 60) {
			h++;
			m -= 60;
		}
		while (m < 0) {
			h--;
			m += 60;
		}
		return new Heure(h, m);
	}

	public Heure retarderDe(float m) {
		Heure h = new Heure(0, m);
		return this.plus(h);
	}

	@Override
	public String toString() {
		return (heure + ":" + minute);
	}

	public double[] toTab() {
		return new double[] { heure, minute };
	}

	public String toCSV() {
		return ("" + heure + ", " + minute);
	}

	public double toNumber() {
		return (heure + (minute / 60));
	}
}

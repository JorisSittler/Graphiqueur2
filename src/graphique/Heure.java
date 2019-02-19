package graphique;
public class Heure {
	int heure;
	float minute;

	public Heure(int h, float m) {
		heure = h;
		minute = m;
	}

	public Heure(int h) {
		heure = h;
		minute = 0;
	}
	
	public Heure plus(Heure a) {
		int h;
		float m;

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

	public String toString() {
		return (heure + ":" + minute);
	}

	public float[] toTab(){
		return new float[]{heure, minute};
	}
	public String toCSV(){
		return ("" + heure + ", " + minute);
	}
	
	public float toNumber() {
		return (heure + (minute / 60));
	}
}

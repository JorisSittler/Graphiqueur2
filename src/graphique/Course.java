package graphique;
import java.util.ArrayList;
import java.util.List;

public class Course {
	int numero;
	ArrayList<PointDePassage> parcours;

	public Course(int n, List<PointDePassage> liste) {
		numero = n;
		parcours = new ArrayList<PointDePassage>(liste);
	}

	public Course(int n) {
		numero = n;
		parcours = null;
	}

	public Course(ArrayList<String[]> liste) {
		numero = 0;
		parcours = new ArrayList<PointDePassage>();
		for (String[] tab : liste) {
			parcours.add(new PointDePassage(tab));
		}
		System.out.println(this);
	}

	public void retarderDe(float m) {
		for (PointDePassage p : parcours) {
			p.retarderDe(m);
		}
	}

	public String toString() {
		String s = "\n" + numero;
		for (PointDePassage p : parcours) {
			s = s + "\n" + p;
		}
		return s;
	}
}

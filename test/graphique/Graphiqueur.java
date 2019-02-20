package graphique;

import graphique.export.ExportCsv;

public class Graphiqueur {

	public static void main(String[] args) {
		// Heure h = new Heure(12, 51);
		// System.out.println(h);

		// Heure h2 = new Heure(-1, 21);
		// System.out.println(h.plus(h2));
		// Heure u = new Heure(1);
		// System.out.println(h.plus(h2.plus(u)));
		// System.out.println(h.plus(h2.plus(u)).toNumber());
		//
		// System.out.println(h.retarderDe(9));
		// System.out.println(h.retarderDe(-9));

		// PointDePassage p = new PointDePassage(h, 42);
		// System.out.println(p);
		// PointDePassage o = new PointDePassage(h.retarderDe(15), 24);
		// System.out.println(o);
		//
		// List l = new ArrayList();
		// l.add(p); l.add(o);
		// Course c = new Course(26, l);
		// System.out.println(c);
		//
		// c.retarderDe(92);
		// System.out.println(c);

		// ExportCsv.generateCsvFile("D:\\Users\\Joris\\Documents\\graphiqueur\\pipo.csv", c);
		ExportCsv.readCsv("F:\\Users\\Joris\\Documents\\BAROUF\\graphiqueur\\pipo.csv");
	}

}

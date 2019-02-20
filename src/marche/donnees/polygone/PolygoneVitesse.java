package marche.donnees.polygone;

import java.util.ArrayList;

public class PolygoneVitesse {
	private ArrayList<SegmentPolygone> courbe;

	public ArrayList<SegmentPolygone> getCourbe() {
		return courbe;
	}

	public PolygoneVitesse(ArrayList<SegmentPolygone> courbe) {
		super();
		this.courbe = courbe;
	}

	public void setCourbe(ArrayList<SegmentPolygone> courbe) {
		this.courbe = courbe;
	}

	public PolygoneVitesse() {
		super();
		courbe = new ArrayList<SegmentPolygone>();
	}
}

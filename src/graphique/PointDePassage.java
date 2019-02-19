package graphique;
public class PointDePassage {
	Heure heure;
	float pk;
	
	
	public PointDePassage(Heure h, float k){
		heure = h;
		pk=k;
	}
	public PointDePassage(String[] tab){
		heure = new Heure(Integer.parseInt(tab[0]), Float.parseFloat(tab[1]));
		pk = Float.parseFloat(tab[2]);
	}
	
	public void retarderDe(float m){
		heure = heure.retarderDe(m);
	}
	public String toString(){
		return (heure + ", " + pk);
	}
	public String toCSV(){
		return ("" + heure.toCSV() + ", " + pk);
	}
}

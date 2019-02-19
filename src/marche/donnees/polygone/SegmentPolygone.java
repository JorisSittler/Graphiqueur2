package marche.donnees.polygone;

public class SegmentPolygone {
	private double pkDebut;
	/** distance en km, sauf s'il s'agit d'un arr�t : dur�e en minutes */
	private double longueur;
	/** en km/h */
	private double vitessePlafond;

	/**
	 * 
	 * @param pkDebut
	 * @param longueur
	 *            la distance en km, sauf s'il s'agit d'un arr�t : dur�e en
	 *            minutes
	 * @param vitessePlafond
	 *            en km/h
	 */
	public SegmentPolygone(double pkDebut, double longueur, double vitessePlafond) {
		super();
		this.pkDebut = pkDebut;
		this.longueur = longueur;
		this.vitessePlafond = vitessePlafond;
	}

	/**
	 * Permet de cr�er un segment avec les m�mes donn�es que celui en param�tre
	 * sauf la vitesse
	 * 
	 * @param base
	 * @param nouvelleVitesse
	 */
	public SegmentPolygone(SegmentPolygone base, double nouvelleVitesse) {
		this.pkDebut = base.getPkDebut();
		this.longueur = base.getLongueur();
		this.vitessePlafond = nouvelleVitesse;
	}

	/** distance en km, sauf s'il s'agit d'un arr�t : dur�e en minutes */
	public double getLongueur() {
		return longueur;
	}

	public double getPkDebut() {
		return pkDebut;
	}

	/** en km/h */
	public double getVitessePlafond() {
		return vitessePlafond;
	}

	/** distance en km, sauf s'il s'agit d'un arr�t : dur�e en minutes */
	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	public void setPkDebut(double pkDebut) {
		this.pkDebut = pkDebut;
	}

	/** en km/h */
	public void setVitessePlafond(double vitessePlafond) {
		this.vitessePlafond = vitessePlafond;
	}

}

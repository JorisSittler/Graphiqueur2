package graphique.donnees;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private int numero;
	private ArrayList<PointDePassage> parcours;

	/**
	 * retarder tous les horaires de la course de m minutes
	 * 
	 * @param m
	 */
	public void retarderDe(float m) {
		for (PointDePassage p : parcours) {
			p.retarderDe(m);
		}
	}

	/**
	 * retarder tous les horaires de la course de h Heures
	 * 
	 * @param m
	 */
	public void retarderDe(Heure h) {
		for (PointDePassage p : parcours) {
			p.retarderDe(h);
		}
	}

	/**
	 * permet de décaler les pk et d'inverser le sens
	 * 
	 * @param depart
	 * @param inverserSens
	 */
	public void decalerPK(double depart, boolean inverserSens) {
		for (PointDePassage p : parcours) {
			if (inverserSens) {
				p.pk = depart - p.pk;
			} else {
				p.pk += depart;
			}
		}
	}

	public Course(int n, List<PointDePassage> liste) {
		numero = n;
		parcours = new ArrayList<PointDePassage>(liste);
	}

	/**
	 * 
	 * @param n
	 *            le numéro
	 */
	public Course(int n) {
		numero = n;
		parcours = null;
	}

	/**
	 * numéro par défaut : 0
	 * 
	 * @param liste
	 *            la liste des points de passage
	 */
	public Course(ArrayList<String[]> liste) {
		numero = 0;
		parcours = new ArrayList<PointDePassage>();
		for (String[] tab : liste) {
			parcours.add(new PointDePassage(tab));
		}
		System.out.println(this);
	}

	/**
	 * constructeur par défaut : numéro = 0 et parcours vide
	 */
	public Course() {
		numero = 0;
		parcours = new ArrayList<PointDePassage>();
	}

	@Override
	public String toString() {
		String s = "\n" + numero;
		for (PointDePassage p : parcours) {
			s = s + "\n" + p;
		}
		return s;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public ArrayList<PointDePassage> getParcours() {
		return parcours;
	}

	public void setParcours(ArrayList<PointDePassage> parcours) {
		this.parcours = parcours;
	}
}

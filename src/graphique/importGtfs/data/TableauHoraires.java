package graphique.importGtfs.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TableauHoraires {
	private static final String PERIODE_DE_CIRCULATION = "Période de circulation";
	String idLigne;
	String directionId;
	Map<String, Trip> listeTrips;
	List<Stop> listeArrets;
	List<List<String>> tableau;

	/**
	 * g�n�re le Tableau avec un filtre sur la ligne et une direction
	 * 
	 * @param idLigne
	 * @param directionId
	 * @param listeTrips
	 *            la map dans laquelle chercher les trajets nous concernant
	 */
	public TableauHoraires(String idLigne, String directionId, Map<String, Trip> listeTrips) {
		this.idLigne = idLigne;
		this.directionId = directionId;
		this.listeArrets = new ArrayList<>();
		this.listeTrips = new HashMap<>();

		for (Trip trip : listeTrips.values()) {
			if (trip.getRouteId().contentEquals(idLigne) && trip.getDirectionId().contentEquals(directionId)) {
				this.listeTrips.put(trip.getTripId(), trip);
			}
		}
	}

	/**
	 * g�n�re le Tableau sans contrainte de direction
	 * 
	 * @param idLigne
	 * @param listeTrips
	 */
	public TableauHoraires(String idLigne, Map<String, Trip> listeTrips) {
		this.idLigne = idLigne;
		this.listeArrets = new ArrayList<>();
		this.listeTrips = new HashMap<>();

		for (Trip trip : listeTrips.values()) {
			if (trip.getRouteId().contentEquals(idLigne)) {
				this.listeTrips.put(trip.getTripId(), trip);
			}
		}
	}

	public TableauHoraires(List<String> idLignes, Map<String, Trip> listeTrips) {
		this(idLignes.get(0), listeTrips);
		if (idLignes.size() > 1) {
			for (int i = 1; i < idLignes.size(); i++) {
				this.ajouterLigne(idLignes.get(i), listeTrips);
			}
		}
	}

	/**
	 * 
	 * @param idLignes
	 *            liste d'ID lignes qui seront toutes ajout�es au tableau
	 * @param direction
	 *            valable pour toutes les routes
	 * @param listeTrips
	 */
	public TableauHoraires(List<String> idLignes, String direction, Map<String, Trip> listeTrips) {
		this(idLignes.get(0), direction, listeTrips);
		if (idLignes.size() > 1) {
			for (int i = 1; i < idLignes.size(); i++) {
				this.ajouterLigne(idLignes.get(i), direction, listeTrips);
			}
		}
	}

	/**
	 * Ajoute au tableau une autre ligne afin de regrouper les trajets
	 * 
	 * @param ligne
	 * @param direction
	 */
	public void ajouterLigne(String ligne, String direction, Map<String, Trip> trips) {
		for (Trip trip : trips.values()) {
			if (trip.getRouteId().contentEquals(ligne) && trip.getDirectionId().contentEquals(direction)) {
				this.listeTrips.put(trip.getTripId(), trip);
			}
		}
	}

	/**
	 * Ajoute au tableau une autre ligne afin de regrouper les trajets
	 * 
	 * @param ligne
	 */
	public void ajouterLigne(String ligne, Map<String, Trip> trips) {
		for (Trip trip : trips.values()) {
			if (trip.getRouteId().contentEquals(ligne)) {
				this.listeTrips.put(trip.getTripId(), trip);
			}
		}
	}

	private void genererListeArrets() {
		Iterator<Trip> it = listeTrips.values().iterator();
		// prendre un trajet pour initialiser une liste pr�liminaire
		Trip tr;
		if (it.hasNext()) {
			tr = it.next();
			for (StopTime st : tr.getListeArrets().values()) {
				listeArrets.add(st.getArret());
			}
		}

		// parcourir les trajets, et s'ils contiennent des nouveaux trajets les ajouter au bon endroit
		while (it.hasNext()) {
			tr = it.next();
			// premi�re v�rification : est-ce qu'on a un arr�t pas encore list� ?
			List<Stop> listeArretsDeCeTrip = new ArrayList<>();
			for (StopTime st : tr.getListeArrets().values()) {
				listeArretsDeCeTrip.add(st.getArret());
			}
			// si oui on entre dans le m�canisme de compl�tion de la listeArrets
			if (!listeArrets.containsAll(listeArretsDeCeTrip)) {
				for (int indexCeTrip = 0; indexCeTrip < listeArretsDeCeTrip.size(); indexCeTrip++) {
					// quand on a un arr�t � ajouter, on cherche � quel endroit de la liste l'ajouter
					if (!listeArrets.contains(listeArretsDeCeTrip.get(indexCeTrip))) {
						// recherche de l'arr�t d�j� r�pertori� (dans la liste) pr�c�dent dans la s�quence (du trip)
						int positionListeAvant = -1;
						for (int indexListeAvant = indexCeTrip; indexListeAvant >= 0; indexListeAvant--) {
							if (listeArrets.contains(listeArretsDeCeTrip.get(indexListeAvant))) {
								positionListeAvant = listeArrets.indexOf(listeArretsDeCeTrip.get(indexListeAvant));
								break;
							}
						}
						// Une fois cet arr�t d�j� r�pertori� trouv�, on ins�re le nouveau juste apr�s dans la liste
						listeArrets.add(positionListeAvant + 1, listeArretsDeCeTrip.get(indexCeTrip));
					}
				}
			}
		}
	}

	public List<List<String>> getTableau() {
		if (tableau == null) {
			tableau = genererTableau();
		}
		return tableau;
	}

	/**
	 * G�n�re une liste de lignes pour tableau
	 * 
	 * @return
	 */
	public List<List<String>> genererTableau() {
		// cr�er le tableau qui contiendra toutes les colonnes
		List<List<String>> resultat = new ArrayList<>(getListeTrips().size() + 1);

		// colonne initiale : noms des arr�ts
		List<String> colonneNoms = new ArrayList<>();
		colonneNoms.add("Numéro");
		colonneNoms.add(PERIODE_DE_CIRCULATION);
		// Ajouter le nom de chaque arr�t
		for (int i = 0; i < getListeArrets().size(); i++) {
			colonneNoms.add(getListeArrets().get(i).getStopName());
		}
		resultat.add(colonneNoms);

		// ajouter les infos et l'horaire pour chaque trajet
		// TODO parcourir dans le bon ordre ??
		// construction par colonne, plus pratique pour ordonner
		for (Trip tr : getListeTrips().values()) {
			// dans les deux premi�res lignes on ajoute les infos sp�cifiques
			List<String> colonne = new ArrayList<>();
			colonne.add(tr.getTripHeadsign());
			colonne.add(tr.getCalendar().afficher());

			// Pour chaque autre ligne (on commence donc � 2 et non � 0), on
			// cherche l'horaire de d�part correspondant
			for (int i = 2; i < colonneNoms.size(); i++) {
				boolean desservi = false;
				for (StopTime st : tr.getListeArrets().values()) {
					// on parcourt tous les arr�ts du trajet en esp�rant
					// tomber sur le bon
					if (colonneNoms.get(i).contentEquals(st.getArret().getStopName())) {
						desservi = true;
						colonne.add(i, st.getDepartureTime());
					}
				}
				// si on n'a pas trouv� l'horaire, on marque comme non desservi
				if (!desservi) {
					colonne.add(i, "   -   ");
				}
			}
			resultat.add(colonne);
		}
		System.out.println("nombre de trips : " + listeTrips.size() + ", nombre de lignes cr��es : " + resultat.size());
		return resultat;
	}

	/**
	 * Repr�sente chaque course par une ligne
	 */
	public void afficherCsvEnLignes() {
		List<List<String>> tab = getTableau();
		for (List<String> ligne : tab) {
			System.out.println(ligne);
		}
	}

	/**
	 * Repr�sente chaque course par une ligne
	 */
	public void afficherCsvEnLignes(int serviceId) {
		List<List<String>> tab = getTableau();
		for (List<String> ligne : tab) {
			if (ligne.get(1).contentEquals(Integer.toString(serviceId))
					|| ligne.get(1).contentEquals(PERIODE_DE_CIRCULATION)) {
				System.out.println(ligne);
			}
		}
	}

	/**
	 * Repr�sente chaque course sous forme de colonne
	 */
	public void afficherCsvEnColonnes() {
		List<List<String>> tab = getTableau();
		for (int indexColonne = 0; indexColonne < tab.get(0).size(); indexColonne++) {
			// on construit chaque ligne du tableau en parcourant toutes les colonnes
			StringBuffer sb = new StringBuffer();
			for (int indexLigne = 0; indexLigne < tab.size(); indexLigne++) {
				sb.append(tab.get(indexLigne).get(indexColonne)).append(",");
			}
			System.out.println(sb.toString());
		}
	}

	public void afficherServices() {
		Set<Service> set = new HashSet<Service>();
		for (Trip tr : listeTrips.values()) {
			set.add(tr.getCalendar());
		}
		System.out.println(set);
	}

	public List<Stop> getListeArrets() {
		if (listeArrets.size() == 0) {
			genererListeArrets();
		}
		return listeArrets;
	}

	public String getIdLigne() {
		return idLigne;
	}

	public String getDirectionId() {
		return directionId;
	}

	public Map<String, Trip> getListeTrips() {
		return listeTrips;
	}
}

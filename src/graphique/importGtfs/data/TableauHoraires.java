package graphique.importGtfs.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TableauHoraires {
	private static final String PÉRIODE_DE_CIRCULATION = "Période de circulation";
	String idLigne;
	String directionId;
	Map<String, Trip> listeTrips;
	List<Stop> listeArrets;
	List<List<String>> tableau;

	/**
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
			if (trip.getRouteId().contentEquals(idLigne)) {
				if (trip.getDirectionId().contentEquals(directionId)) {
					this.listeTrips.put(trip.getTripId(), trip);
				}
			}
		}
	}

	private void genererListeArrets() {
		Iterator<Trip> it = listeTrips.values().iterator();
		// prendre un trajet pour initialiser une liste préliminaire
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
			// première vérification : est-ce qu'on a un arrêt pas encore listé
			// ?
			List<Stop> listeArretsDeCeTrip = new ArrayList<>();
			for (StopTime st : tr.getListeArrets().values()) {
				listeArretsDeCeTrip.add(st.getArret());
			}
			// si oui on entre dans le mécanisme de complétion de la listeArrets
			if (!listeArrets.containsAll(listeArretsDeCeTrip)) {
				for (int indexCeTrip = 0; indexCeTrip < listeArretsDeCeTrip.size(); indexCeTrip++) {
					// quand on a un arrêt à ajouter, on cherche à quel endroit de la liste l'ajouter
					if (!listeArrets.contains(listeArretsDeCeTrip.get(indexCeTrip))) {
						// recherche de l'arrêt déjà répertorié (dans la liste) précédent dans la séquence (du trip)
						int positionListeAvant = -1;
						for (int indexListeAvant = indexCeTrip; indexListeAvant >= 0; indexListeAvant--) {
							if (listeArrets.contains(listeArretsDeCeTrip.get(indexListeAvant))) {
								positionListeAvant = listeArrets.indexOf(listeArretsDeCeTrip.get(indexListeAvant));
								break;
							}
						}
						// Une fois cet arrêt déjà répertorié toruvé, on insère le nouveau juste après dans la liste
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
	 * Génère une liste de lignes pour tableau
	 * 
	 * @return
	 */
	public List<List<String>> genererTableau() {
		// créer le tableau qui contiendra toutes les colonnes
		List<List<String>> resultat = new ArrayList<>(getListeTrips().size() + 1);

		// colonne initiale : noms des arrêts
		List<String> colonneNoms = new ArrayList<>();
		colonneNoms.add("Numéro");
		colonneNoms.add(PÉRIODE_DE_CIRCULATION);
		// Ajouter le nom de chaque arrêt
		for (int i = 0; i < getListeArrets().size(); i++) {
			colonneNoms.add(getListeArrets().get(i).getStopName());
		}
		resultat.add(colonneNoms);

		// ajouter les infos et l'horaire pour chaque trajet
		// TODO parcourir dans le bon ordre ??
		// construction par colonne, plus pratique pour ordonner
		for (Trip tr : getListeTrips().values()) {
			// dans les deux premières lignes on ajoute les infos spécifiques
			List<String> colonne = new ArrayList<>();
			colonne.add(tr.getTripHeadsign());
			colonne.add(tr.getServiceId());

			// Pour chaque autre ligne (on commence donc à 2 et non à 0), on
			// cherche l'horaire de départ correspondant
			for (int i = 2; i < colonneNoms.size(); i++) {
				boolean desservi = false;
				for (StopTime st : tr.getListeArrets().values()) {
					// on parcourt tous les arrêts du trajet en espérant
					// tomber sur le bon
					if (colonneNoms.get(i).contentEquals(st.getArret().getStopName())) {
						desservi = true;
						colonne.add(i, st.getDepartureTime());
					}
				}
				// si on n'a pas trouvé l'horaire, on marque comme non
				// desservi
				if (!desservi) {
					colonne.add(i, "   -   ");
				}
			}
			resultat.add(colonne);
		}
		System.out.println("nombre de trips : " + listeTrips.size() + ", nombre de lignes créées : " + resultat.size());
		return resultat;
	}

	/**
	 * Représente chaque course par une ligne
	 */
	public void afficherCsvEnLignes() {
		List<List<String>> tab = getTableau();
		for (List<String> ligne : tab) {
			System.out.println(ligne);
		}
	}

	/**
	 * Représente chaque course par une ligne
	 */
	public void afficherCsvEnLignes(int serviceId) {
		List<List<String>> tab = getTableau();
		for (List<String> ligne : tab) {
			if (ligne.get(1).contentEquals(Integer.toString(serviceId))
					|| ligne.get(1).contentEquals(PÉRIODE_DE_CIRCULATION)) {
				System.out.println(ligne);
			}
		}
	}

	/**
	 * Représente chaque course sous forme de colonne
	 */
	public void afficherCsvEnColonnes() {
		List<List<String>> tab = getTableau();
		for (int indexColonne = 0; indexColonne < tab.get(0).size(); indexColonne++) {
			// on construit chaque ligne du tableau en parcourant toutes les
			// colonnes
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

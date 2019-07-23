package graphique.importGtfs.lecture;

import graphique.importGtfs.data.Service;
import graphique.importGtfs.data.Stop;
import graphique.importGtfs.data.StopTime;
import graphique.importGtfs.data.Trip;

import java.util.HashMap;
import java.util.Map;

public class LireContenuFichier {

	/**
	 * Affiche tous les trajets ayant le headsign demandé
	 * 
	 * @param listeTrips
	 * @param headSign
	 */
	public static void afficherNumero(Map<String, Trip> listeTrips, String headSign) {
		Map<String, Trip> listeTripsLigne = new HashMap<>();
		for (Trip trip : listeTrips.values()) {
			if (trip.getTripHeadsign().contentEquals(headSign)) {
				listeTripsLigne.put(trip.getTripId(), trip);
			}
		}
		System.out.println("toutes les occurrences du head_sign " + headSign + " : " + listeTripsLigne);
	}

	/**
	 * Affiche tous les trajets correspondant à la ligne demandée dans les deux sens
	 * 
	 * @param listeTrips
	 * @param idLigne
	 */
	public static void afficherTripsLigne(Map<String, Trip> listeTrips, String idLigne) {
		Map<String, Trip> listeTripsLignePair = new HashMap<>();
		Map<String, Trip> listeTripsLigneImpair = new HashMap<>();
		Map<String, Trip> listeTripsLignePasDeSens = new HashMap<>();
		// Parcourir tous les trajets, et si la ligne correspond l'ajouter à la liste du sens correspondant
		for (Trip trip : listeTrips.values()) {
			if (trip.getRouteId().contentEquals(idLigne)) {
				if (trip.getDirectionId().contentEquals("1")) {
					listeTripsLigneImpair.put(trip.getTripId(), trip);
				} else if (trip.getDirectionId().contentEquals("0")) {
					listeTripsLignePair.put(trip.getTripId(), trip);
				} else {
					listeTripsLignePasDeSens.put(trip.getTripId(), trip);
				}
			}
		}
		System.out.println("sens impair : " + listeTripsLigneImpair);
		System.out.println("sens pair : " + listeTripsLignePair);
		System.out.println("sens non indiqué : " + listeTripsLignePasDeSens);
	}

	/**
	 * Initialise les trajet et arrêt dans chaque StopTime, listeArrets dans Trip et dans Stop.<br/>
	 * /!\ Crée donc les liens indispensables aux autres traitements
	 * 
	 * @param listeTrips
	 * @param listeStops
	 * @param listeStopTimes
	 */
	public static void associerArrets(Map<String, Trip> listeTrips, Map<String, Stop> listeStops,
			Map<String, StopTime> listeStopTimes, Map<String, Service> listeServices) {
		// on parcourt tous les stopTime pour faire les associations dans les deux sens avec les Stop et Trip
		for (StopTime stopTime : listeStopTimes.values()) {
			Trip trip = listeTrips.get(stopTime.getTripId());
			if (trip != null) {
				stopTime.setTrajet(trip);
				trip.getListeArrets().put(Integer.valueOf(stopTime.getStopSequence()), stopTime);
			}
			Stop stop = listeStops.get(stopTime.getStopId());
			if (stop != null) {
				stopTime.setArret(stop);
				stop.getListeArrets().put(stopTime.getTripId(), stopTime);
			}
		}
		// on parcourt les Trip pour y associer les Service (calendar.txt)
		for (Trip trip : listeTrips.values()) {
			trip.setCalendar(listeServices.get(trip.getServiceId()));
		}
	}
}

package graphique.importGtfs.lecture;

import graphique.importGtfs.data.Service;
import graphique.importGtfs.data.Stop;
import graphique.importGtfs.data.StopTime;
import graphique.importGtfs.data.TableauHoraires;
import graphique.importGtfs.data.Trip;
import graphique.importGtfs.data.builder.ServiceBuilder;
import graphique.importGtfs.data.builder.StopBuilder;
import graphique.importGtfs.data.builder.StopTimeBuilder;
import graphique.importGtfs.data.builder.TripBuilder;

import java.util.Map;

public class MainStar {
	static String directory = "..\\fichiers\\";

	public static void main(String[] args) {
		String nomGTFS = "GTFS_2020.1.1.0_20200831_20201018\\";
		long dateDebut = System.currentTimeMillis();

		TripBuilder tb = new TripBuilder();
		StopBuilder sb = new StopBuilder();
		StopTimeBuilder stb = new StopTimeBuilder();
		ServiceBuilder svb = new ServiceBuilder();

		Map<String, Trip> listeTrips = tb.recupererToutAPartirDuFichier(directory, nomGTFS);
		// System.out.println("nombre de trips : " + listeTrips.size());
		// System.out.println(listeTrips.get("6262"));

		Map<String, Stop> listeStops = sb.recupererToutAPartirDuFichier(directory, nomGTFS);
		// System.out.println("nombre de stops : " + listeStops.size());
		// System.out.println(listeStops.get("stop_id"));
		// System.out.println(listeStops.get("1042"));

		Map<String, StopTime> listeStopTimes = stb.recupererToutAPartirDuFichier(directory, nomGTFS);
		// System.out.println("nombre de stopTimes : " + listeStopTimes.size());

		Map<String, Service> listeServices = svb.recupererToutAPartirDuFichier(directory, nomGTFS);

		LireContenuFichier.associerArrets(listeTrips, listeStops, listeStopTimes, listeServices);
		long dateFin = System.currentTimeMillis();
		// System.out.println(("temps de calcul (ms) : " + (dateFin - dateDebut)));

		// System.out.println(listeTrips.get("6262").afficher());
		// LireContenuFichier.afficherTripsLigne(listeTrips, "0041");

		TableauHoraires th35i = new TableauHoraires("0035", "1", listeTrips);
		// th35i.ajouterLigne("0150", "1", listeTrips);
		th35i.afficherCsvEnLignes();

		System.out.println("\t\tautre sens : ");

		TableauHoraires th35p = new TableauHoraires("0035", "0", listeTrips);
		// th35i.ajouterLigne("0150", "0", listeTrips);
		th35p.afficherCsvEnLignes();

		// TableauHoraires thMetro = new TableauHoraires("1001", "", listeTrips);
		// thMetro.afficherServices();
		// thMetro.afficherCsvEnColonnes();
	}
}

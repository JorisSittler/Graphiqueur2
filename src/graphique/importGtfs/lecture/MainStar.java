package graphique.importGtfs.lecture;

import java.util.Map;

import graphique.importGtfs.data.Service;
import graphique.importGtfs.data.Stop;
import graphique.importGtfs.data.StopTime;
import graphique.importGtfs.data.TableauHoraires;
import graphique.importGtfs.data.Trip;
import graphique.importGtfs.data.builder.ServiceBuilder;
import graphique.importGtfs.data.builder.StopBuilder;
import graphique.importGtfs.data.builder.StopTimeBuilder;
import graphique.importGtfs.data.builder.TripBuilder;

public class MainStar {
	static String directory = "C:\\Users\\joris\\Documents\\Transports\\GTFS\\";

	public static void main(String[] args) {
		String nomGTFS = "STAR GTFS_2022.1.1.0_20220920_20221023\\";
		long dateDebut = System.currentTimeMillis();

		TripBuilder tb = new TripBuilder();
		StopBuilder sb = new StopBuilder();
		StopTimeBuilder stb = new StopTimeBuilder();
		ServiceBuilder svb = new ServiceBuilder();

		Map<String, Trip> listeTrips = tb.recupererToutAPartirDuFichier(directory, nomGTFS);
		// System.out.println("nombre de trips : " + listeTrips.size());
		// System.out.println(listeTrips.get("22110032159"));

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

		// System.out.println(listeTrips.get("22110032159").afficher());
		// LireContenuFichier.afficherTripsLigne(listeTrips, "1002");

//		TableauHoraires thB1 = new TableauHoraires("1002", "1", listeTrips);
//		// th35i.ajouterLigne("0150", "1", listeTrips);
//		thB1.afficherCsvEnLignes();

		System.out.println("\t\tautre sens : ");

		TableauHoraires thB0 = new TableauHoraires("1002", "0", listeTrips);
		// th35i.ajouterLigne("0150", "0", listeTrips);
		thB0.afficherCsvEnLignes();

		// TableauHoraires thMetro = new TableauHoraires("1001", "", listeTrips);
		// thMetro.afficherServices();
		// thMetro.afficherCsvEnColonnes();
	}
}

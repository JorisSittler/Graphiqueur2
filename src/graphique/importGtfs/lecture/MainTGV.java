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

public class MainTGV {
	static String directory = "..\\fichiers\\";

	public static void main(String[] args) {
		String nomGTFS = "export_gtfs_voyages\\";
		long dateDebut = System.currentTimeMillis();

		TripBuilder tb = new TripBuilder();
		StopBuilder sb = new StopBuilder();
		StopTimeBuilder stb = new StopTimeBuilder();
		ServiceBuilder svb = new ServiceBuilder();

		Map<String, Trip> listeTrips = tb.recupererToutAPartirDuFichier(directory, nomGTFS);
		System.out.println("nombre de trips : " + listeTrips.size());
		// System.out.println(listeTrips.get("OCESN002101R0100110355"));

		Map<String, Stop> listeStops = sb.recupererToutAPartirDuFichier(directory, nomGTFS);
		System.out.println("nombre de stops : " + listeStops.size());
		// System.out.println(listeStops.get("stop_id"));
		// System.out.println(listeStops.get("StopArea:OCE87116178"));

		Map<String, StopTime> listeStopTimes = stb.recupererToutAPartirDuFichier(directory, nomGTFS);
		System.out.println("nombre de stopTimes : " + listeStopTimes.size());

		Map<String, Service> listeServices = svb.recupererToutAPartirDuFichier(directory, nomGTFS);

		LireContenuFichier.associerArrets(listeTrips, listeStops, listeStopTimes, listeServices);
		long dateFin = System.currentTimeMillis();
		System.out.println(("temps de calcul (ms) : " + (dateFin - dateDebut)));

		TableauHoraires thSup1 = new TableauHoraires("OCE235", "1", listeTrips);
		TableauHoraires thSup2 = new TableauHoraires("OCE235", "0", listeTrips);

		// thSup1.afficherServices();
		thSup1.afficherCsvEnLignes();
		thSup2.afficherCsvEnLignes();

		// TableauHoraires thParisStras1 = new TableauHoraires("OCESN-87113001-87212027", "1", listeTrips);
		// thParisStras1.ajouterLigne("OCE1209972", "1", listeTrips);
		// thParisStras1.afficherCsvEnLignes();
		//
		// TableauHoraires thParisStras2 = new TableauHoraires("OCESN-87113001-87212027", "0", listeTrips);
		// thParisStras2.ajouterLigne("OCE1209972", "0", listeTrips);
		// thParisStras2.afficherCsvEnLignes();
	}
}

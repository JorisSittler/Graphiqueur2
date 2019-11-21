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

public class MainTer {
	static String directory = "..\\fichiers\\";

	public static void main(String[] args) {
		String nomGTFS = "export-ter-gtfs-last 201911\\";
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

		// System.out.println(listeTrips.get("OCESN857777F0100162507").afficher());
		// System.out.println(listeTrips.get("OCESN857700F0100162429").afficher());
		// System.out.println(listeTrips.get("OCESN857691F0100162422").afficher());

		// afficherTripsLigne(listeTrips, "OCE17");
		// afficherNumero(listeTrips, "857691");
		// afficherNumero(listeTrips, "857678");

		TableauHoraires thLMRennes = new TableauHoraires("OCE17", "0", listeTrips);
		TableauHoraires thRennesLaval = new TableauHoraires("OCE164687", "0", listeTrips);
		// TableauHoraires thStrBsl = new TableauHoraires("OCE200734", "1", listeTrips);
		// TableauHoraires thBslStr = new TableauHoraires("OCE200734", "0", listeTrips);

		// System.out.println(thLMRennes.getListeTrips());
		// System.out.println(thStrBsl.getListeTrips());

		// thLMRennes.afficherCsvEnLignes(1640);
		thRennesLaval.afficherServices();
		thRennesLaval.afficherCsvEnLignes();
		// thStrBsl.afficherCsvEnLignes();
		// thStrBsl.afficherCsvEnColonnes();
		// thBslStr.afficherCsvEnLignes();
	}
}

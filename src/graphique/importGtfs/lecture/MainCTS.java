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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class MainCTS {
	static String directory = "..\\fichiers\\";

	public static void main(String[] args) {
		String nomGTFS = "google_transit_CTS_20200813\\";

		TripBuilder tb = new TripBuilder();
		StopBuilder sb = new StopBuilder();
		StopTimeBuilder stb = new StopTimeBuilder();
		ServiceBuilder svb = new ServiceBuilder();

		Map<String, Trip> listeTrips = tb.recupererToutAPartirDuFichier(directory, nomGTFS);
		Map<String, Stop> listeStops = sb.recupererToutAPartirDuFichier(directory, nomGTFS);
		Map<String, StopTime> listeStopTimes = stb.recupererToutAPartirDuFichier(directory, nomGTFS);
		Map<String, Service> listeServices = svb.recupererToutAPartirDuFichier(directory, nomGTFS);

		LireContenuFichier.associerArrets(listeTrips, listeStops, listeStopTimes, listeServices);

		ArrayList<String> ligneB = new ArrayList<>(Arrays.asList("B-1146", "B-1201", "B-1234", "B-1239", "B-1250",
				"B-1272", "B-1273"));

		// TableauHoraires thB = new TableauHoraires(ligneB, "1", listeTrips);
		// thB.afficherCsvEnLignes();

		TableauHoraires thA = new TableauHoraires("A-1146", "1", listeTrips);
		thA.afficherCsvEnLignes();
	}
}

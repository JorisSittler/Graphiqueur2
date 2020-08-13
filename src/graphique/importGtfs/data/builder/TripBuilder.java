package graphique.importGtfs.data.builder;

import graphique.importGtfs.data.Trip;

import java.util.Map;

public class TripBuilder extends DataElementBuilder {

	@Override
	// version SNCF et CTS
	public Trip decouperArrayEnObjet(DataStructure data) {
		return new Trip(data.getData(0), data.getData(1), data.getData(2), data.getData(3), data.getData(4),
				data.getData(5), data.getData(6));
	}

	// version STAR
	// public Trip decouperArrayEnObjet(DataStructure data) {
	// return new Trip(data.getData(0), data.getData(1), data.getData(2), data.getData(3), data.getData(5),
	// data.getData(6), data.getData(7));
	// }

	// version CTS

	public Map<String, Trip> recupererToutAPartirDuFichier(String directory, String nomGTFS) {
		// On ne peut pas cast directement de la map de DataElement en map de Trip... donc bricolage sale
		@SuppressWarnings("unchecked")
		Map<String, Trip> listeTrips = (Map<String, Trip>) (Map<String, ?>) lireFichier(directory, nomGTFS, "trips");
		listeTrips.remove("trip_id");
		return listeTrips;
	}
}

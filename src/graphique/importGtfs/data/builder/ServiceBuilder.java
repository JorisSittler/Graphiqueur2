package graphique.importGtfs.data.builder;

import graphique.importGtfs.data.DataElement;
import graphique.importGtfs.data.Service;

import java.util.Map;

public class ServiceBuilder extends DataElementBuilder {

	/**
	 * Pour charger une partie découpée du fichier
	 * 
	 * @param directory
	 * @param nomGTFS
	 * @param suffixeFichier
	 * @return
	 */
	public Map<String, Service> recupererToutAPartirDuFichier(String directory, String nomGTFS) {
		// On ne peut pas cast directement de la map de DataElement en map de StopTime... donc bricolage sale
		@SuppressWarnings("unchecked")
		Map<String, Service> listeStops = (Map<String, Service>) (Map<String, ?>) lireFichier(directory, nomGTFS,
				"calendar");
		return listeStops;
		// TODO ajouter les exceptions depuis calendar_dates.txt
	}

	@Override
	public DataElement decouperArrayEnObjet(DataStructure data) {
		return new Service(data.getData(0), stringToBoleen(data.getData(1)), stringToBoleen(data.getData(2)),
				stringToBoleen(data.getData(3)), stringToBoleen(data.getData(4)), stringToBoleen(data.getData(5)),
				stringToBoleen(data.getData(6)), stringToBoleen(data.getData(7)), data.getData(8), data.getData(9));
	}

	protected boolean stringToBoleen(String s) {
		if (s.contentEquals("1")) {
			return true;
		} else {
			return false;
		}
	}
}

package graphique.importGtfs.data.builder;

import graphique.importGtfs.data.DataElement;
import graphique.importGtfs.data.Service;

import java.util.List;
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
		Map<String, Service> listeServices = (Map<String, Service>) (Map<String, ?>) lireFichier(directory, nomGTFS,
				"calendar");

		// ajouter les exceptions depuis calendar_dates.txt
		List<String[]> listeExceptions = lireFichierEnListe(directory, nomGTFS, "calendar_dates");
		for (String[] exc : listeExceptions) {
			if (listeServices.containsKey(exc[0])) {
				// si l'exception concerne un service déjà connu : on l'ajoute au service
				listeServices.get(exc[0]).ajouterException(exc);
			} else {
				// sinon on crée le service
				listeServices.put(exc[0], new Service(exc));
			}
		}

		return listeServices;
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

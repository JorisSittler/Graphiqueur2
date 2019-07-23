package graphique.importGtfs.data.builder;

import graphique.importGtfs.data.DataElement;
import graphique.importGtfs.data.StopTime;

import java.util.Map;

public class StopTimeBuilder extends DataElementBuilder {

	@Override
	public DataElement decouperArrayEnObjet(DataStructure data) {
		return new StopTime(data.getData(0), data.getData(1), data.getData(2), data.getData(3), data.getData(4),
				data.getData(5), data.getData(6), data.getData(7), data.getData(8));
	}

	/**
	 * Pour charger une partie découpée du fichier
	 * 
	 * @param directory
	 * @param nomGTFS
	 * @param suffixeFichier
	 * @return
	 */
	public Map<String, StopTime> recupererToutAPartirDuFichier(String directory, String nomGTFS, String suffixeFichier) {
		String nomFichier = "stop_times" + suffixeFichier;
		// On ne peut pas cast directement de la map de DataElement en map de StopTime... donc bricolage sale
		@SuppressWarnings("unchecked")
		Map<String, StopTime> listeStops = (Map<String, StopTime>) (Map<String, ?>) lireFichier(directory, nomGTFS,
				nomFichier);
		return listeStops;
	}

	public Map<String, StopTime> recupererToutAPartirDuFichier(String directory, String nomGTFS) {
		return recupererToutAPartirDuFichier(directory, nomGTFS, "");
	}
}

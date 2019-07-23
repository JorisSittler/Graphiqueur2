package graphique.importGtfs.data.builder;

import graphique.importGtfs.data.Stop;

import java.util.Map;

public class StopBuilder extends DataElementBuilder {

	@Override
	public Stop decouperArrayEnObjet(DataStructure data) {
		return new Stop(data.getData(0), data.getData(1), data.getData(2), data.getData(3), data.getData(4),
				data.getData(5), data.getData(6), data.getData(7), data.getData(8));
	}

	public Map<String, Stop> recupererToutAPartirDuFichier(String directory, String nomGTFS) {
		// On ne peut pas cast directement de la map de DataElement en map de Stop... donc bricolage sale
		@SuppressWarnings("unchecked")
		Map<String, Stop> listeStops = (Map<String, Stop>) (Map<String, ?>) lireFichier(directory, nomGTFS, "stops");
		listeStops.remove("stop_id");
		// associer le Stop parent
		for (Stop st : listeStops.values()) {
			if (st.getParentStation() != null) {
				st.setStopParent(listeStops.get(st.getParentStation()));
			}
		}
		return listeStops;
	}

}

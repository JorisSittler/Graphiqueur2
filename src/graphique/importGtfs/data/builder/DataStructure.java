package graphique.importGtfs.data.builder;

import java.util.List;

public class DataStructure {
	/**
	 * Le tableau des données lues
	 */
	String[] array;
	/**
	 * Correspondance entre les numéros de colonne et les champs
	 */
	List<Integer> mapping;

	public DataStructure(String[] array) {
		super();
		this.array = array;
	}

	public String getData(int i) {
		if (i < array.length) {
			return enleverGuillemets(array[i]);
		} else {
			return null;
		}
	}

	public static String enleverGuillemets(String entree) {
		if (entree.startsWith("\"")) {
			return entree.replaceAll("\"", "");
		} else {
			return entree;
		}
	}
}

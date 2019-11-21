package graphique.importGtfs.data.builder;

import graphique.importGtfs.data.DataElement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DataElementBuilder {
	/**
	 * largeur maximale du csv, peut être paramétrée pour chaque type (optimisation)
	 */
	protected static int largeurCSV = 12;

	/**
	 * Lit un fichier .txt au format csv et renvoie la liste des Arrays contenus
	 * 
	 * @param nomGTFS
	 * @param nomFichier
	 * @return
	 */
	public Map<String, DataElement> lireFichier(String directory, String nomGTFS, String nomFichier) {
		Map<String, DataElement> resultat = new HashMap<>();
		String ligne = null;
		String cheminFichier = directory + nomGTFS + nomFichier + ".txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(cheminFichier));
			while ((ligne = br.readLine()) != null) {
				DataElement result = null;
				if (ligne != null) {
					String[] splitData = ligne.split("\\s*,\\s*");
					result = decouperArrayEnObjet(new DataStructure(splitData));
					resultat.put(result.getIdentifiant(), result);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("erreur désérialization : " + e);
			System.exit(-1);
		}
		return resultat;
	}

	/**
	 * Lit un fichier .txt au format csv et renvoie la liste des Arrays contenus
	 * 
	 * @param nomGTFS
	 * @param nomFichier
	 * @return
	 */
	public List<String[]> lireFichierEnListe(String directory, String nomGTFS, String nomFichier) {
		List<String[]> resultat = new ArrayList<String[]>();
		String ligne = null;
		String cheminFichier = directory + nomGTFS + nomFichier + ".txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(cheminFichier));
			while ((ligne = br.readLine()) != null) {
				if (ligne != null) {
					String[] splitData = ligne.split("\\s*,\\s*");
					resultat.add(splitData);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("erreur désérialization : " + e);
			System.exit(-1);
		}
		return resultat;
	}

	/**
	 * Lit une ligne (csv convertie en Array) et renvoie l'élément créé avec
	 * 
	 * @param ligne
	 * @return
	 */
	public abstract DataElement decouperArrayEnObjet(DataStructure ligne);

}

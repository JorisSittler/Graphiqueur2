package graphique.export;

import graphique.donnees.Course;
import graphique.donnees.PointDePassage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExportCsv {

	public static void generateCsvFile(String sFileName, Course c) {
		try {
			FileWriter writer = new FileWriter(sFileName);

			writer.append("" + c.getNumero() + "\n");
			for (PointDePassage p : c.getParcours()) {
				writer.append(p.toCSV());
				writer.append('\n');
			}

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Course readCsv(String file) {

		BufferedReader crunchifyBuffer = null;
		ArrayList<String[]> liste = new ArrayList<String[]>();

		try {
			String crunchifyLine = null;
			crunchifyBuffer = new BufferedReader(new FileReader(file));

			while ((crunchifyLine = crunchifyBuffer.readLine()) != null) {
				// System.out.println("Raw CSV data: " + crunchifyLine);
				String h = crunchifyCSVtoArrayList(crunchifyLine)[0];
				String m = crunchifyCSVtoArrayList(crunchifyLine)[1];
				String k = crunchifyCSVtoArrayList(crunchifyLine)[2];
				String[] tab = { h, m, k };
				liste.add(tab);
				// System.out.println(h + ":" + m + ", " + k);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (crunchifyBuffer != null)
					crunchifyBuffer.close();
			} catch (IOException crunchifyException) {
				crunchifyException.printStackTrace();
			}
		}
		return new Course(liste);
	}

	public static String[] crunchifyCSVtoArrayList(String crunchifyCSV) {
		String[] crunchifyResult = new String[3];

		if (crunchifyCSV != null) {
			String[] splitData = crunchifyCSV.split("\\s*,\\s*");
			for (int i = 0; (i < splitData.length && i < 3); i++) {
				if (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
					crunchifyResult[i] = splitData[i].trim();
				}
			}
		}
		// System.out.println(crunchifyResult[0] + " g " + crunchifyResult[1]);
		return crunchifyResult;
	}
}
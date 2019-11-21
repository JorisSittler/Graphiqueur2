package graphique.importGtfs.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Service extends DataElement {
	static DateTimeFormatter format = DateTimeFormatter.BASIC_ISO_DATE;

	String serviceId;
	boolean lundi, mardi, mercredi, jeudi, vendredi, samedi, dimanche;
	String startDate;
	String endDate;
	LocalDate dateDebut;
	LocalDate dateFin;
	List<LocalDate> exceptionsAjout;
	List<LocalDate> exceptionsMoins;

	/**
	 * Constructeur utilisé depuis calendar.txt
	 * 
	 * @param serviceId
	 * @param lundi
	 * @param mardi
	 * @param mercredi
	 * @param jeudi
	 * @param vendredi
	 * @param samedi
	 * @param dimanche
	 * @param startDate
	 * @param endDate
	 */
	public Service(String serviceId, boolean lundi, boolean mardi, boolean mercredi, boolean jeudi, boolean vendredi,
			boolean samedi, boolean dimanche, String startDate, String endDate) {
		super();
		this.serviceId = serviceId;
		this.lundi = lundi;
		this.mardi = mardi;
		this.mercredi = mercredi;
		this.jeudi = jeudi;
		this.vendredi = vendredi;
		this.samedi = samedi;
		this.dimanche = dimanche;
		this.startDate = startDate;
		this.endDate = endDate;

		if (!"start_date".contentEquals(startDate)) {
			dateDebut = LocalDate.parse(startDate, format);
			dateFin = LocalDate.parse(endDate, format);
		}
		this.exceptionsAjout = new ArrayList<LocalDate>();
		this.exceptionsMoins = new ArrayList<LocalDate>();
	}

	/**
	 * Constructeur spécial : Service défini seulement par une exception
	 * 
	 * @param exc
	 *            le tableau contenant les paramètres de l'exception : service_id,date,exception_type
	 */
	public Service(String[] exc) {
		super();
		this.exceptionsAjout = new ArrayList<LocalDate>();
		this.exceptionsMoins = new ArrayList<LocalDate>();

		// si l'entrée fournie n'est pas vide
		if (exc != null && exc[2] != null) {
			this.serviceId = exc[0];
			LocalDate date = LocalDate.parse(exc[1], format);
			if ("1".contentEquals(exc[2])) {
				// service ajouté à la date spécifié
				this.exceptionsAjout.add(date);
			} else if ("2".contentEquals(exc[2])) {
				// service supprimmé à cette date
				this.exceptionsMoins.add(date);
			}
		}
	}

	/**
	 * Ajout d'une exception
	 * 
	 * @param exc
	 *            le tableau contenant les paramètres de l'exception : service_id,date,exception_type
	 */
	public void ajouterException(String[] exc) {
		// si l'entrée fournie n'est pas vide et correspond au service actuel
		if (exc != null && exc[2] != null && exc[0] == this.serviceId) {
			LocalDate date = LocalDate.parse(exc[1], format);
			if ("1".contentEquals(exc[2])) {
				// service ajouté à la date spécifié
				this.exceptionsAjout.add(date);
			} else if ("2".contentEquals(exc[2])) {
				// service supprimmé à cette date
				this.exceptionsMoins.add(date);
			}
		}
	}

	@Override
	public String getIdentifiant() {
		return serviceId;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("\nService [serviceId=");
		sb.append(serviceId);
		if (lundi)
			sb.append(" lundi ");
		if (mardi)
			sb.append(" mardi ");
		if (mercredi)
			sb.append(" mercredi ");
		if (jeudi)
			sb.append(" jeudi ");
		if (vendredi)
			sb.append(" vendredi ");
		if (samedi)
			sb.append(" samedi ");
		if (dimanche)
			sb.append(" dimanche ");
		sb.append(", dateDebut=").append(dateDebut).append(", dateFin=").append(dateFin);

		if (exceptionsAjout != null && exceptionsAjout.size() > 0) {
			sb.append("; dates supplémentaires : ");
			for (LocalDate date : exceptionsAjout) {
				sb.append(date).append(", ");
			}
		}
		if (exceptionsMoins != null && exceptionsMoins.size() > 0) {
			sb.append("; dates supprimé : ");
			for (LocalDate date : exceptionsMoins) {
				sb.append(date).append(", ");
			}
		}

		sb.append("]");

		return sb.toString();
	}
}

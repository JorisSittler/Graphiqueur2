package graphique.importGtfs.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Service extends DataElement {
	static DateTimeFormatter format = DateTimeFormatter.BASIC_ISO_DATE;

	String serviceId;
	boolean lundi, mardi, mercredi, jeudi, vendredi, samedi, dimanche;
	String startDate;
	String endDate;
	LocalDate dateDebut;
	LocalDate dateFin;

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
		sb.append(", dateDebut=").append(dateDebut).append(", dateFin=").append(dateFin).append("]");

		return sb.toString();
	}
}

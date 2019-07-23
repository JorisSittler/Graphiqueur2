package graphique.importGtfs.data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Stop extends DataElement {
	String stopId;
	String stopName;
	String stopDesc;
	String stopLat;
	String stopLong;
	String zoneId;
	String stopUrl;
	String locationType;
	String parentStation;

	/**
	 * La liste des dessertes à ce Stop, identifiés par trip_id
	 */
	ConcurrentMap<String, StopTime> listeArrets;

	Stop stopParent;

	public Stop(String stopId, String stopName, String stopDesc, String stopLat, String stopLong, String zoneId,
			String stopUrl, String locationType, String parentStation) {
		super();
		this.stopId = stopId;
		this.stopName = stopName;
		this.stopDesc = stopDesc;
		this.stopLat = stopLat;
		this.stopLong = stopLong;
		this.zoneId = zoneId;
		this.stopUrl = stopUrl;
		this.locationType = locationType;
		this.parentStation = parentStation;
		listeArrets = new ConcurrentHashMap<>();
	}

	public Stop(String stopId, String stopName, String stopDesc, String parentStation) {
		super();
		this.stopId = stopId;
		this.stopName = stopName;
		this.stopDesc = stopDesc;
		this.parentStation = parentStation;
		listeArrets = new ConcurrentHashMap<>();
	}

	@Override
	public String toString() {
		String r = "Stop [stopId=" + stopId + ", stopName=" + stopName;
		if (stopParent != null) {
			r += ", parentLocation=" + stopParent.toString() + "]";
		}
		return r;
	}

	public String toStringDetail() {
		return "Stop [stopId=" + stopId + ", stopName=" + stopName + ", stopDesc=" + stopDesc + ", stopLat=" + stopLat
				+ ", stopLong=" + stopLong + ", zoneId=" + zoneId + ", stopUrl=" + stopUrl + ", locationType="
				+ locationType + ", parentLocation=" + parentStation + "]";
	}

	public String getStopId() {
		return stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

	public ConcurrentMap<String, StopTime> getListeArrets() {
		return listeArrets;
	}

	public void setListeArrets(ConcurrentMap<String, StopTime> listeArrets) {
		this.listeArrets = listeArrets;
	}

	public String getParentStation() {
		return parentStation;
	}

	public void setParentStation(String parentStation) {
		this.parentStation = parentStation;
	}

	public Stop getStopParent() {
		return stopParent;
	}

	public String getStopDesc() {
		return stopDesc;
	}

	public void setStopParent(Stop stopParent) {
		this.stopParent = stopParent;
	}

	public String getStopName() {
		if (stopName.length() <= 4) {
			// si le "stop_name" est court on suppose que c'est en fait un identifiant donc on affiche
			// plutôt la description qui contient son nom complet
			return stopDesc;
		} else {
			return stopName;
		}
	}

	@Override
	public String getIdentifiant() {
		return stopId;
	}
}

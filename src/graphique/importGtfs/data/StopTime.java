package graphique.importGtfs.data;

public class StopTime extends DataElement {
	String tripId;
	String arrivalTime;
	String departureTime;
	String stopId;
	String stopSequence;
	String stopHeadsign;
	String pickUpType;
	String dropOffType;
	String shapeDistTraveled;

	Stop arret;
	Trip trajet;

	public StopTime(String tripId, String arrivalTime, String departureTime, String stopId, String stopSequence,
			String stopHeadsign, String pickUpType, String dropOffType, String shapeDistTraveled) {
		super();
		this.tripId = tripId;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopId = stopId;
		this.stopSequence = stopSequence;
		this.stopHeadsign = stopHeadsign;
		this.pickUpType = pickUpType;
		this.dropOffType = dropOffType;
		this.shapeDistTraveled = shapeDistTraveled;
	}

	@Override
	public String toString() {
		return "StopTime [tripId=" + tripId + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime
				+ ", stopId=" + arret.toString() + ", stopSequence=" + stopSequence + ", stopHeadsign=" + stopHeadsign;
	}

	public String toStringDetail() {
		return "StopTime [tripId=" + tripId + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime
				+ ", stopId=" + arret.toString() + ", stopSequence=" + stopSequence + ", stopHeadsign=" + stopHeadsign
				+ ", pickUpType=" + pickUpType + ", dropOffType=" + dropOffType + ", shapeDistTraveled="
				+ shapeDistTraveled + "]";
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getStopId() {
		return stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

	public Stop getArret() {
		return arret;
	}

	public void setArret(Stop arret) {
		this.arret = arret;
	}

	public Trip getTrajet() {
		return trajet;
	}

	public void setTrajet(Trip trajet) {
		this.trajet = trajet;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getStopSequence() {
		return stopSequence;
	}

	public String getStopHeadsign() {
		return stopHeadsign;
	}

	public String getPickUpType() {
		return pickUpType;
	}

	public String getDropOffType() {
		return dropOffType;
	}

	public String getShapeDistTraveled() {
		return shapeDistTraveled;
	}

	@Override
	public String getIdentifiant() {
		return tripId + "/" + stopId;
	}
}

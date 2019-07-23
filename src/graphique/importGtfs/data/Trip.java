package graphique.importGtfs.data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Trip extends DataElement {
	String routeId;
	String serviceId;
	String tripId;
	String tripHeadsign;
	String directionId;
	String blockId;
	String shapeId;

	/**
	 * La liste des arrêts desservis, avec comme clé le numéro de séquence
	 */
	ConcurrentMap<Integer, StopTime> listeArrets;

	/**
	 * les dates de service
	 */
	Service calendar;

	public Trip(String routeId, String serviceId, String tripId, String tripHeadsign, String directionId,
			String blockId, String shapeId) {
		super();
		this.routeId = routeId;
		this.serviceId = serviceId;
		this.tripId = tripId;
		this.tripHeadsign = tripHeadsign;
		this.directionId = directionId;
		this.blockId = blockId;
		this.shapeId = shapeId;
		listeArrets = new ConcurrentHashMap<>();
	}

	public Trip(String routeId, String serviceId, String tripId, String tripHeadsign, String directionId) {
		super();
		this.routeId = routeId;
		this.serviceId = serviceId;
		this.tripId = tripId;
		this.tripHeadsign = tripHeadsign;
		this.directionId = directionId;
		listeArrets = new ConcurrentHashMap<>();
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getTripHeadsign() {
		return tripHeadsign;
	}

	public void setTripHeadsign(String tripHeadsign) {
		this.tripHeadsign = tripHeadsign;
	}

	public String getDirectionId() {
		return directionId;
	}

	public void setDirectionId(String directionId) {
		this.directionId = directionId;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getShapeId() {
		return shapeId;
	}

	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
	}

	public ConcurrentMap<Integer, StopTime> getListeArrets() {
		return listeArrets;
	}

	public void setListeArrets(ConcurrentMap<Integer, StopTime> listeArrets) {
		this.listeArrets = listeArrets;
	}

	@Override
	public String toString() {
		return this.afficher();
	}

	public String toStringMoche() {
		return "Trip [routeId=" + routeId + ", serviceId=" + serviceId + ", tripId=" + tripId + ", tripHeadsign="
				+ tripHeadsign + ", directionId=" + directionId + ", blockId=" + blockId + ", shapeId=" + shapeId
				+ ", listeArrets=" + listeArrets.toString() + "]";
	}

	public String afficher() {
		StringBuffer sb = new StringBuffer("\nTrip [routeId=" + routeId + ", serviceId=" + serviceId + ", tripId="
				+ tripId + ", tripHeadsign=" + tripHeadsign + ", directionId=" + directionId + "\n");

		if (listeArrets != null && !listeArrets.isEmpty()) {
			for (StopTime st : listeArrets.values()) {
				sb.append("\t");
				if (st.getArret() != null) {
					sb.append(st.getArret().getStopName());
				}
				sb.append(" arr : ").append(st.getArrivalTime()).append(", dep : ").append(st.getDepartureTime())
						.append("\n");
			}
		}
		return sb.toString();
	}

	@Override
	public String getIdentifiant() {
		return tripId;
	}

	public Service getCalendar() {
		return calendar;
	}

	public void setCalendar(Service calendar) {
		this.calendar = calendar;
	}
}

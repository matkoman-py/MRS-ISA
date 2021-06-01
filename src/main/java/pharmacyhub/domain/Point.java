package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Point extends BaseEntity {
	
	@Column(nullable = false)
	private double latitude;
	@Column(nullable = false)
	private double longitude;
	
	public Point() {
		
	}
	
	public Point(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}

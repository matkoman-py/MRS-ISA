package pharmacyhub.dto;

public class DrugstoreDto {
	String id;
	String name;
	String adress;
    String city;
    double rating;
    Integer pharmacistAppointmentPrice;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Integer getPharmacistAppointmentPrice() {
		return pharmacistAppointmentPrice;
	}
	public void setPharmacistAppointmentPrice(Integer pharmacistAppointmentPrice) {
		this.pharmacistAppointmentPrice = pharmacistAppointmentPrice;
	}
	public DrugstoreDto(String id, String name, String adress, String city, double rating,
			Integer pharmacistAppointmentPrice) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.city = city;
		this.rating = rating;
		this.pharmacistAppointmentPrice = pharmacistAppointmentPrice;
	}
	public DrugstoreDto() {
		super();
	}
    
}

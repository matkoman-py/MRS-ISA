package pharmacyhub.domain;

public class Manufacturer {

	private String id;
	private String name;
	private Location location;
	private String phoneNumber;

	public Manufacturer() {
		super();
	}

	public Manufacturer(String id, String name, Location location, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.phoneNumber = phoneNumber;
	}

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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}

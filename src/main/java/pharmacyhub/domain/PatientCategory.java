package pharmacyhub.domain;

public class PatientCategory {

	private String name;
	private int requieredPoints;
	private int discount;

	public PatientCategory() {

	}

	public PatientCategory(String name, int requieredPoints, int discount) {
		super();
		this.name = name;
		this.requieredPoints = requieredPoints;
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRequieredPoints() {
		return requieredPoints;
	}

	public void setRequieredPoints(int requieredPoints) {
		this.requieredPoints = requieredPoints;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}

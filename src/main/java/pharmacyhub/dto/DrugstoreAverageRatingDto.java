package pharmacyhub.dto;

public class DrugstoreAverageRatingDto {
	
	private double averageRate;
	private int numberOfRates;
	
	public DrugstoreAverageRatingDto() {
		
	}

	public DrugstoreAverageRatingDto(double averageRate, int numberOfRates) {
		super();
		this.averageRate = averageRate;
		this.numberOfRates = numberOfRates;
	}

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public int getNumberOfRates() {
		return numberOfRates;
	}

	public void setNumberOfRates(int numberOfRates) {
		this.numberOfRates = numberOfRates;
	}
	
}

package pharmacyhub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pharmacyhub.domain.users.Dermatologist;

@Entity
public class Employment extends BaseEntity {

	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "drugstore_id")
	private Drugstore drugstore;

	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "dermatologist_id")
	private Dermatologist dermatologist;

	@Column
	private String workingHoursFrom;

	@Column
	private String workingHoursTo;
	
	public Employment() {
		
	}

	public Employment(Drugstore drugstore, Dermatologist dermatologist, String workingHoursFrom,
			String workingHoursTo) {
		super();
		this.drugstore = drugstore;
		this.dermatologist = dermatologist;
		this.workingHoursFrom = workingHoursFrom;
		this.workingHoursTo = workingHoursTo;
	}

	public Drugstore getDrugstore() {
		return drugstore;
	}

	public void setDrugstore(Drugstore drugstore) {
		this.drugstore = drugstore;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public String getWorkingHoursFrom() {
		return workingHoursFrom;
	}

	public void setWorkingHoursFrom(String workingHoursFrom) {
		this.workingHoursFrom = workingHoursFrom;
	}

	public String getWorkingHoursTo() {
		return workingHoursTo;
	}

	public void setWorkingHoursTo(String workingHoursTo) {
		this.workingHoursTo = workingHoursTo;
	}

}

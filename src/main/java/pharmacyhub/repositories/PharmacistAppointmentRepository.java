package pharmacyhub.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.users.Dermatologist;
import pharmacyhub.domain.users.Pharmacist;

public interface PharmacistAppointmentRepository extends JpaRepository<PharmacistAppointment, String> {
	List<PharmacistAppointment> findByPharmacistId(String pharmacistId);
	List<PharmacistAppointment> findByPatientId(String patientId);
	List<PharmacistAppointment> findByPharmacistAndProcessed(Pharmacist pharmacist, boolean processed);
	@Transactional
	void deleteByPharmacist(Pharmacist pharmacist);
}

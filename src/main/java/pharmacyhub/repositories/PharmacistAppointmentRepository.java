package pharmacyhub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import pharmacyhub.domain.PharmacistAppointment;
import pharmacyhub.domain.users.Pharmacist;

public interface PharmacistAppointmentRepository extends JpaRepository<PharmacistAppointment, String> {
	List<PharmacistAppointment> findByPharmacistId(String pharmacistId);
	List<PharmacistAppointment> findByPatientId(String patientId);
}

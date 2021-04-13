package pharmacyhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import pharmacyhub.domain.PharmacistAppointment;

public interface PharmacistAppointmentRepository extends JpaRepository<PharmacistAppointment, String> {
	PharmacistAppointment findByPharmacistId(String pharmacistId);
}

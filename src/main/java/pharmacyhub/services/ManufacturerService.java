package pharmacyhub.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Manufacturer;
import pharmacyhub.repositories.ManufacturerRepository;

@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	public ArrayList<Manufacturer> readAll() {
		return manufacturerRepository.readAll();
	}

}

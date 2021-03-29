package pharmacyhub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.DrugType;
import pharmacyhub.repositories.DrugTypeRepository;

@Service
public class DrugTypeService {

	@Autowired
	private DrugTypeRepository drugTypeRepository;

	public List<DrugType> findAll() {
		return drugTypeRepository.findAll();
	}

	public DrugType findById(String id) {
		return drugTypeRepository.findById(id).orElse(null);
	}

}

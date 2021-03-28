package pharmacyhub.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pharmacyhub.domain.Drug;
import pharmacyhub.domain.DrugType;
import pharmacyhub.domain.Ingrediant;
import pharmacyhub.domain.Manufacturer;
import pharmacyhub.repositories.DrugRepository;
import pharmacyhub.repositories.DrugTypeRepository;
import pharmacyhub.repositories.IngrediantRepository;
import pharmacyhub.repositories.ManufacturerRepository;

@Service
public class DrugService{
	
	@Autowired
	private DrugRepository drugRepository;
	
	@Autowired
	private DrugTypeRepository drugTypeRepository;
	
	@Autowired
	private IngrediantRepository ingrediantRepository;
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
		
	public void seedDrugs() {
		DrugType drugType = new DrugType();
		Manufacturer manufacturer = new Manufacturer();
		Ingrediant ingredient = new Ingrediant();
		ArrayList<Ingrediant> ingrediants = new ArrayList<>();
		
		ingrediants.add(ingredient);
		drugRepository.add(new Drug("id1", "Gentamicin", "cream", true, drugTypeRepository.read("id1"), 
				manufacturerRepository.read("id1"), null, ingrediantRepository.findAll(), "mast 0,1%, 15g", 10));
	}
	
	public Collection<Drug> returnDrugs(String drugName,String drugType,String drugForm,String drugManufacturer,String drugReceipt){
		Collection<Drug> allDrugs = readAll();
		Collection<Drug> wantedDrugs = new ArrayList<>();
		//String sta = "";
		for(Drug medicine:allDrugs) {
			//sta += " " + medicine.getType().getName() + " " + drugType + "x \n";
			if((medicine.getName().equals(drugName) || drugName.equals("0")) &&
			   (medicine.getType().getName().equals(drugType) || drugType.equals("0")) &&
			   (medicine.getForm().equals(drugForm) || drugForm.equals("0")) &&
			   (medicine.getManufacturer().getName().equals(drugManufacturer) || drugManufacturer.equals("0")) &&
			   ( ((medicine.isReceipt() && drugReceipt.equals("Yes")) || (!medicine.isReceipt() && drugReceipt.equals("No")))  || drugReceipt.equals("0"))) {
				//sta += " uso i ovde";
				wantedDrugs.add(medicine);
			}
		}
		return wantedDrugs;
	}
	
	public ArrayList<Drug> readAll()
	{
		return drugRepository.readAll();
	}
	
	public Drug add(Drug drug)
	{
		return drugRepository.add(drug);
	}

}

package pharmacyhub.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import pharmacyhub.domain.Location;
import pharmacyhub.domain.Manufacturer;

@Repository
public class ManufacturerRepository {
	
	private ArrayList<Manufacturer> manufacturers;
	
	public ManufacturerRepository() {
		seedManufacturers();
	}
	
	private void seedManufacturers() {
		
		Location location1 = new Location("123", "Batajnički drum b.b.", "Beograd", "Serbia");
		Location location2 = new Location("124", "Beogradski put bb", "Vršac", "Serbia");
	
		manufacturers = new ArrayList<Manufacturer>();
		manufacturers.add(new Manufacturer("id1", "Galenika", location1, "+381 11 307 1000"));
		manufacturers.add(new Manufacturer("id2", "Hemofarm", location2, "+381 13 803 100"));
	}
	
	public ArrayList<Manufacturer> readAll(){
		return manufacturers;
	}
	
	public Manufacturer read(String id){
		return manufacturers.stream()
			.filter(drugType -> drugType.getId().equals(id))
			.findFirst()
			.orElse(null);
	}
}

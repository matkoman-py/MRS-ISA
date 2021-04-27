package pharmacyhub.dto.search;

import pharmacyhub.domain.Location;

public class DrugstoreSearchDto {
	
	public String name;
	public Location location;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}


}

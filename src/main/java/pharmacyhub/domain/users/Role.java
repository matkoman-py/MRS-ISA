package pharmacyhub.domain.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pharmacyhub.domain.BaseEntity;

@Entity
@Table(name="roles")
public class Role extends BaseEntity implements GrantedAuthority{

	private static final long serialVersionUID = 1L;


    @Column(name="name")
    String name;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

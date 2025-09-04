package placement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Embeddable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "college")
@Embeddable
public class College {
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    private String name;
	    private String location;

	    public College() {}

	    public College(long id, String name, String location) {
	        this.id = id;
	        this.name = name;
	        this.location = location;
	    }

	    // Getters & Setters
	    public long getId() { return id; }
	    public void setId(long id) { this.id = id; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public String getLocation() { return location; }
	    public void setLocation(String location) { this.location = location; }
	}




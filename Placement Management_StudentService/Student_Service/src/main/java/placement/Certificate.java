package placement;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "certificate")
public class Certificate {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)


	    private long id;
	    private int year;
	    @ManyToOne(fetch = FetchType.LAZY)   // or EAGER as needed
	    @JoinColumn(name = "college_id")
	    private College college;

	    public Certificate() {}
	    public Certificate(long id, int year, College college) {
	        this.id = id;
	        this.year = year;
	        this.college = college;
	    }

	    // Getters & Setters
	    public long getId() { return id; }
	    public void setId(long id) { this.id = id; }

	    public int getYear() { return year; }
	    public void setYear(int year) { this.year = year; }

	    public College getCollege() { return college; }
	    public void setCollege(College college) { this.college = college; }
	}




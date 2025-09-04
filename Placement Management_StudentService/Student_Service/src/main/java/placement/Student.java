package placement;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;




@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "studentdet")
//Student.java
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 
	private long id;
 
	private String name;
 
	@ManyToOne
    @JoinColumn(name = "college_id")
	@Embedded
	private College college;
	
	@Column(unique = true)
	private long roll;
 
	private String qualification;
 
	private String course;
 
	private int year;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "certificate_id")
	private Certificate certificate;
 

	private long hallTicketNo;

 public Student() {}
 public Student(long id, String name, College college, long roll, String qualification,
                String course, int year, Certificate certificate, int hallTicketNo) {
     this.id = id;
     this.name = name;
     this.college = college;
     this.roll = roll;
     this.qualification = qualification;
     this.course = course;
     this.year = year;
     this.certificate = certificate;
     this.hallTicketNo = hallTicketNo;
      }

 // Getters & Setters
 public long getId() { return id; }
 public void setId(long id) { this.id = id; }

 public String getName() { return name; }
 public void setName(String name) { this.name = name; }

 public College getCollege() { return college; }
 public void setCollege(College college) { this.college = college; }

 public long getRoll() { return roll; }
 public void setRoll(long roll) { this.roll = roll; }

 public String getQualification() { return qualification; }
 public void setQualification(String qualification) { this.qualification = qualification; }

 public String getCourse() { return course; }
 public void setCourse(String course) { this.course = course; }

 public int getYear() { return year; }
 public void setYear(int year) { this.year = year; }

 public Certificate getCertificate() { return certificate; }
 public void setCertificate(Certificate certificate) { this.certificate = certificate; }

 public long getHallTicketNo() { return hallTicketNo; }
 public void setHallTicketNo(long l) { this.hallTicketNo = l; }
}

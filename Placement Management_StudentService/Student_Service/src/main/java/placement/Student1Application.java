package placement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("placement")
@SpringBootApplication
public class Student1Application {

	public static void main(String[] args) {
		SpringApplication.run(Student1Application.class, args);
	}

}

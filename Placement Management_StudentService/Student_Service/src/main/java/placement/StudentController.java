package placement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("studentdet")  // Ensure the path matches with your frontend request
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Create new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // Update student details
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        // Try to find the student by ID
        Optional<Student> existingStudentOptional = studentRepository.findById(id);

        // Check if the student exists
        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            
            // Update the student's fields with the new data
            existingStudent.setName(student.getName());
            existingStudent.setCollege(student.getCollege());
            existingStudent.setRoll(student.getRoll());
            existingStudent.setQualification(student.getQualification());
            existingStudent.setCourse(student.getCourse());
            existingStudent.setYear(student.getYear());
            existingStudent.setCertificate(student.getCertificate());
            existingStudent.setHallTicketNo((int) student.getHallTicketNo());

            // Save the updated student
            studentRepository.save(existingStudent);

            // Return the updated student with HTTP status OK
            return ResponseEntity.ok(existingStudent);
        } else {
            // If the student doesn't exist, return a 404 not found response
            return ResponseEntity.notFound().build();
        }
    }


    // Delete a student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}

package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        System.out.println("student = " + student);

        String email = student.getEmail();
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다, email = "+ email);
        }

//        studentByEmail.ifPresent(s -> {
//            throw new IllegalStateException("이미 존재하는 이메일입니다, email = "+ s.getEmail());
//        });

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {

        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("존재하지 않는 studentId, studentId = "+ studentId);
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 studentId, studentId = " + studentId));

        if(name != null &&
            name.length() > 0 &&
                !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(),email)){
            student.setEmail(email);
        }
    }
}

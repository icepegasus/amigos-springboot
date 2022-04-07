package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {

            studentRepository.saveAll(List.of(
                    new Student(
                            1L,
                            "Hoya",
                            "gaemung2hoya@iptime.org",
                            LocalDate.of(2017, Month.JULY, 28)
                    ),
                    new Student(
                            2L,
                            "Hoya2",
                            "gaeddong2hoya@iptime.org",
                            LocalDate.of(2007, Month.JULY, 28)
                    )

            ));

        };
    }
}

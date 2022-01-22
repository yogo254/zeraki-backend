package com.zeraki.zerakibackend.app.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, String> {
    List<Student> findByKeywordsContaining(String keywords);

}

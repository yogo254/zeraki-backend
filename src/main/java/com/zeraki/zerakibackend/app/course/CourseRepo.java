package com.zeraki.zerakibackend.app.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, String> {
    Optional<Course> findOneByInstitutionIdAndName(String institutionId, String name);

    List<Course> findByKeywordsContaining(String keywords);
}

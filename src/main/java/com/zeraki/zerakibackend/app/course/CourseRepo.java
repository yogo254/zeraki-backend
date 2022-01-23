package com.zeraki.zerakibackend.app.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepo extends JpaRepository<Course, String> {
    Optional<Course> findOneByInstitutionIdAndName(String institutionId, String name);

    List<Course> findByKeywordsContainingIgnoreCase(String keywords);

    @Query("SELECT c FROM Course c WHERE c.institutionId.id=?1")
    List<Course> findByInstitution(String institutionId);
}

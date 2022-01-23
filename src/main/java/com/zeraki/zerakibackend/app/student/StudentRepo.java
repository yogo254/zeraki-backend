package com.zeraki.zerakibackend.app.student;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepo extends PagingAndSortingRepository<Student, String> {
    List<Student> findByKeywordsContainingIgnoreCase(String keywords);

    @Query("SELECT s FROM Student s WHERE s.courseId.id=?1")
    Slice<Student> findByCourseId(String courseId, PageRequest request);

    @Query("SELECT s FROM Student s WHERE s.courseId.institutionId.id=?1")
    Slice<Student> findByInstitutionId(String institutionId, PageRequest request);

}

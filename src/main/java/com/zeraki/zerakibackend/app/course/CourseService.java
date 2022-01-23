package com.zeraki.zerakibackend.app.course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    public List<Course> findAllByInstitution(String institutionId);

    public List<Course> search(String searchTerm);

    public List<Course> getAllSortedByNameInstitutions();

    public String deleteCourse(String id);

    public Course addCourse(CourseRequest request);

    public Course editCourse(CourseRequest request);

    public Optional<Course> findById(String id);

}

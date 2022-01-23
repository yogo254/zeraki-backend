package com.zeraki.zerakibackend.app.student;

import java.util.List;

import org.springframework.data.domain.Slice;

public interface StudentService {
    public Student addStudent(StudentRequest request);

    public String deleteStudent(String id);

    public Student editStudent(StudentRequest request);

    public List<Student> search(String searchTerm);

    public Slice<Student> findByCourse(String courseId, int page);

    public Slice<Student> findByInstitution(String institutionId, int page);

    public Slice<Student> findAllStudents(int page);

    public Slice<Student> findAllStudentsSorted(int page);

}

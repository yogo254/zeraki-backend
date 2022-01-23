package com.zeraki.zerakibackend.app.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zeraki.zerakibackend.app.course.Course;
import com.zeraki.zerakibackend.app.course.CourseService;
import com.zeraki.zerakibackend.app.exception.NoSuchElementFoundException;
import com.zeraki.zerakibackend.app.util.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseService courseService;

    @Override
    public Student addStudent(StudentRequest request) {
        Optional<Course> course = courseService.findById(request.getCourseId());
        if (course.isPresent()) {
            Student student = new Student();
            student.setName(request.getName());
            student.setCourseId(course.get());
            student.setKeywords(student.toString());
            return studentRepo.save(student);

        }
        throw new NoSuchElementFoundException("No course not found.");

    }

    @Override
    public String deleteStudent(String id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return "Student deleted successfully";
        }

        throw new NoSuchElementFoundException("Student not found.");

    }

    @Override
    public Student editStudent(StudentRequest request) {
        Optional<Course> course = courseService.findById(request.getCourseId());

        if (course.isPresent()) {

            Optional<Student> op = studentRepo.findById(request.getId());

            if (op.isPresent()) {
                Student old = op.get();
                old.setName(request.getName());
                old.setCourseId(course.get());
                old.setKeywords(old.toString());
                return studentRepo.save(old);
            } else
                throw new NoSuchElementFoundException("Student not found.");
        }

        throw new NoSuchElementFoundException("Course not found.");
    }

    @Override
    public List<Student> search(String searchTerm) {
        List<Student> result = new ArrayList<>();
        String[] words = searchTerm.trim().split(" ");
        for (String word : words) {
            result.addAll(studentRepo.findByKeywordsContainingIgnoreCase(word.trim()));

        }

        return result.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Slice<Student> findByCourse(String courseId, int page) {
        PageRequest request = PageRequest.of(page, AppConstants.SIZE);
        return studentRepo.findByCourseId(courseId, request);
    }

    @Override
    public Slice<Student> findByInstitution(String institutionId, int page) {
        PageRequest request = PageRequest.of(page, AppConstants.SIZE);
        return studentRepo.findByInstitutionId(institutionId, request);
    }

    @Override
    public Slice<Student> findAllStudents(int page) {
        PageRequest request = PageRequest.of(page, AppConstants.SIZE);
        return studentRepo.findAll(request);
    }

    @Override
    public Slice<Student> findAllStudentsSorted(int page) {
        PageRequest request = PageRequest.of(page, AppConstants.SIZE, Sort.by("name"));
        return studentRepo.findAll(request);
    }

}

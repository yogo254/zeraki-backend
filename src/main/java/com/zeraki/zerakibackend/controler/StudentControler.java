package com.zeraki.zerakibackend.controler;

import java.util.List;

import com.zeraki.zerakibackend.app.student.Student;
import com.zeraki.zerakibackend.app.student.StudentRequest;
import com.zeraki.zerakibackend.app.student.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/student")
public class StudentControler {
    @Autowired
    private StudentService studentService;

    @PostMapping

    public Student addStudent(@RequestBody StudentRequest request) {
        return studentService.addStudent(request);
    }

    @DeleteMapping
    public String deleteStudent(@RequestParam String id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping
    public Student editStudent(@RequestBody StudentRequest request) {
        return studentService.editStudent(request);
    }

    @GetMapping("/search")
    public List<Student> search(@RequestParam String search) {
        return studentService.search(search);

    }

    @GetMapping("/course")
    public Slice<Student> findByCourse(@RequestParam String courseId, @RequestParam int page) {
        return studentService.findByCourse(courseId, page);

    }

    @GetMapping("/institution")
    public Slice<Student> findByInstitution(@RequestParam String institutionId, @RequestParam int page) {
        return studentService.findByInstitution(institutionId, page);
    }

    @GetMapping
    public Slice<Student> findAllStudents(@RequestParam int page) {
        return studentService.findAllStudents(page);
    }

    @GetMapping("/sorted")
    public Slice<Student> findAllSortedStudents(@RequestParam int page) {
        return studentService.findAllStudentsSorted(page);
    }

}

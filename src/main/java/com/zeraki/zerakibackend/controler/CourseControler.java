package com.zeraki.zerakibackend.controler;

import java.util.List;

import com.zeraki.zerakibackend.app.course.Course;
import com.zeraki.zerakibackend.app.course.CourseRequest;
import com.zeraki.zerakibackend.app.course.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/course")
public class CourseControler {

    @Autowired
    private CourseService courseService;

    @GetMapping("/institution")
    public List<Course> findAllByInstitution(@RequestParam String institutionId) {
        return courseService.findAllByInstitution(institutionId);
    }

    @GetMapping("/search")
    public List<Course> search(@RequestParam String search) {
        return courseService.search(search);
    }

    @GetMapping("/sorted")
    public List<Course> getAllSortedByNameInstitutions() {
        return courseService.getAllSortedByNameInstitutions();
    }

    @DeleteMapping
    public String deleteCourse(@RequestParam String id) {
        return courseService.deleteCourse(id);
    }

    @PostMapping
    public Course addCourse(@RequestBody CourseRequest course) {
        return courseService.addCourse(course);
    }

    @PutMapping
    public Course editCourse(@RequestBody CourseRequest request) {
        return courseService.editCourse(request);
    }

}

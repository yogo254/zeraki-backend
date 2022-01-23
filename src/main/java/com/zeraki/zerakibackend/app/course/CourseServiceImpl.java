package com.zeraki.zerakibackend.app.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zeraki.zerakibackend.app.exception.NoSuchElementFoundException;
import com.zeraki.zerakibackend.app.institution.Institution;
import com.zeraki.zerakibackend.app.institution.InstitutionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private InstitutionRepo institutionRepo;

    @Override
    public List<Course> findAllByInstitution(String institutionId) {

        return courseRepo.findByInstitution(institutionId);
    }

    @Override
    public List<Course> search(String searchTerm) {
        List<Course> result = new ArrayList<>();
        String[] words = searchTerm.trim().split(" ");
        for (String word : words) {
            result.addAll(courseRepo.findByKeywordsContainingIgnoreCase(word.trim()));

        }

        return result.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<Course> getAllSortedByNameInstitutions() {
        return courseRepo.findAll(Sort.by("name"));
    }

    @Override
    public String deleteCourse(String id) {
        if (courseRepo.existsById(id)) {
            courseRepo.deleteById(id);
            return "Course deleted successfuly";
        }

        throw new NoSuchElementFoundException("No institution found.");
    }

    @Override
    public Course addCourse(CourseRequest request) {
        Optional<Institution> institution = institutionRepo.findById(request.getInstitutionId());
        if (institution.isPresent()) {
            Course course = new Course();
            course.setName(request.getName());
            course.setInstitutionId(institution.get());
            course.setKeywords(course.toString());
            return courseRepo.save(course);
        }
        throw new NoSuchElementFoundException("Institution not found.");

    }

    @Override
    public Course editCourse(CourseRequest request) {
        Optional<Institution> institution = institutionRepo.findById(request.getInstitutionId());
        if (institution.isPresent()) {
            Optional<Course> op = courseRepo.findById(request.getId());
            if (op.isPresent()) {
                Course old = op.get();
                old.setInstitutionId(institution.get());
                old.setName(request.getName());
                old.setKeywords(old.toString());

                return courseRepo.save(old);

            } else
                throw new NoSuchElementFoundException("Course not found.");

        }

        throw new NoSuchElementFoundException("Institution not found.");

    }

    @Override
    public Optional<Course> findById(String id) {
        return courseRepo.findById(id);
    }

}

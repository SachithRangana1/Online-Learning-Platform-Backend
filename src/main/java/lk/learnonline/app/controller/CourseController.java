package lk.learnonline.app.controller;

import jakarta.persistence.EntityNotFoundException;
import lk.learnonline.app.dto.CourseDto;
import lk.learnonline.app.entity.Course;
import lk.learnonline.app.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;

@RestController
@RequestMapping("/app/course")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<?> postCourse(@RequestBody CourseDto courseDto) {
        Course createdCourse = courseService.postCourse(courseDto);
        if (createdCourse != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto){
        try {
            return ResponseEntity.ok(courseService.updateCourse(id, courseDto));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(courseService.getCourseById(id));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Course not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Something went wrong"));
        }
    }


}

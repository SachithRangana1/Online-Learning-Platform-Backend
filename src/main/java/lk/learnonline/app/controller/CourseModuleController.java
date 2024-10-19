package lk.learnonline.app.controller;

import jakarta.persistence.EntityNotFoundException;
import lk.learnonline.app.dto.CourseModuleDto;
import lk.learnonline.app.entity.CourseModule;
import lk.learnonline.app.service.module.CourseModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("app/coursemodule")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CourseModuleController {

    private final CourseModuleService courseModuleService;

    @PostMapping
    public ResponseEntity<?> postCourseModule(CourseModuleDto courseModuleDto){
        CourseModule createdCourseModule = courseModuleService.postCourseModule(courseModuleDto);

        if (createdCourseModule != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourseModule);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateCourseModule(@PathVariable Long id, @RequestBody CourseModuleDto courseModuleDto){
        try {
            return ResponseEntity.ok(courseModuleService.updateCourseModule(id, courseModuleDto));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseModuleById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(courseModuleService.getCourseModuleById(id));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCourseModule(){
        return ResponseEntity.ok(courseModuleService.getAllCourseModule());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseModule(@PathVariable Long id){
        try {
            courseModuleService.deleteCourseModule(id);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Course Module not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Something went wrong"));
        }
    }

}

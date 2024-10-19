package lk.learnonline.app.controller;

import jakarta.persistence.EntityNotFoundException;
import lk.learnonline.app.dto.EnrollmentDto;
import lk.learnonline.app.entity.Course;
import lk.learnonline.app.entity.Enrollment;
import lk.learnonline.app.entity.User;
import lk.learnonline.app.service.enrollment.EnrollmentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("app/enrollment")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<?> postEnrollment(@RequestBody EnrollmentDto enrollmentDto){
        Enrollment createdEnrollment = enrollmentService.postEnrollment(enrollmentDto);
        if (createdEnrollment != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEnrollment);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update/{userId}/{courseId}")
    public ResponseEntity<?> updateEnrollment(@PathVariable Long userId, @PathVariable Long courseId, @RequestBody EnrollmentDto enrollmentDto){
        try {
            return ResponseEntity.ok(enrollmentService.updateEnrollment(userId, courseId, enrollmentDto));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/{userId}/{courseId}")
    public ResponseEntity<?> getEnrollment(@PathVariable Long userId, @PathVariable Long courseId){
        try {
            return ResponseEntity.ok(enrollmentService.getEnrollmentById(userId, courseId));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @DeleteMapping("/{userId}/{courseId}")
    public ResponseEntity<?> deleteEnrollment (@PathVariable Long userId, @PathVariable Long courseId) {
        try {
            enrollmentService.deleteEnrollment(userId, courseId);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Enrollment not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Something went wrong"));
        }
    }


}

package lk.learnonline.app.service.enrollment;

import lk.learnonline.app.dto.CommentDto;
import lk.learnonline.app.dto.EnrollmentDto;
import lk.learnonline.app.entity.Comment;
import lk.learnonline.app.entity.Course;
import lk.learnonline.app.entity.Enrollment;
import lk.learnonline.app.entity.User;

import java.util.List;

public interface EnrollmentService {

    Enrollment postEnrollment(EnrollmentDto enrollmentDto);

    Enrollment updateEnrollment(Long userId, Long courseId, EnrollmentDto enrollmentDto);

    Enrollment getEnrollmentById(Long userId, Long courseId);

    List<Enrollment> getAllEnrollments();

    void deleteEnrollment(Long userId, Long courseId);
}

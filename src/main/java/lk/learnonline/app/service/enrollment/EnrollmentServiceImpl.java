package lk.learnonline.app.service.enrollment;

import jakarta.persistence.EntityNotFoundException;
import lk.learnonline.app.dto.EnrollmentDto;
import lk.learnonline.app.entity.Course;
import lk.learnonline.app.entity.Enrollment;
import lk.learnonline.app.entity.User;
import lk.learnonline.app.repository.CourseRepository;
import lk.learnonline.app.repository.EnrollmentRepository;
import lk.learnonline.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService{

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public Enrollment saveOrUpdateEnrollment(Enrollment enrollment, EnrollmentDto enrollmentDto){

        User user = userRepository.findById(enrollmentDto.getUser_id()).orElseThrow(()-> new RuntimeException("User not fond"));
        Course course = courseRepository.findById(enrollmentDto.getCourse_id()).orElseThrow(()-> new RuntimeException("Course not found"));

        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setRegisteredDate(enrollmentDto.getRegisteredDate());

        Optional<Enrollment> havingExistingEnroll = enrollmentRepository.findByUserAndCourse(user, course);
        if (havingExistingEnroll.isPresent()){
            Enrollment existingEnrollUpdate = havingExistingEnroll.get();
            existingEnrollUpdate.setRegisteredDate(enrollmentDto.getRegisteredDate());
            return enrollmentRepository.save(existingEnrollUpdate);
        }else {
            return enrollmentRepository.save(enrollment);
        }
    }

    @Override
    public Enrollment postEnrollment(EnrollmentDto enrollmentDto) {
        return saveOrUpdateEnrollment(new Enrollment(), enrollmentDto);


    }

    @Override
    public Enrollment updateEnrollment(Long userId, Long courseId, EnrollmentDto enrollmentDto) {
        User user = userRepository.findById(enrollmentDto.getUser_id()).orElseThrow(()-> new RuntimeException("User not fond"));
        Course course = courseRepository.findById(enrollmentDto.getCourse_id()).orElseThrow(()-> new RuntimeException("Course not found"));

        Optional<Enrollment> existEnrollment = enrollmentRepository.findByUserAndCourse(user, course);
        if (existEnrollment.isPresent()){
            Enrollment existUpdateToEnroll = existEnrollment.get();
            existUpdateToEnroll.setRegisteredDate(enrollmentDto.getRegisteredDate());
            return enrollmentRepository.save(existUpdateToEnroll);
        }else {
            throw new RuntimeException("Enrollment is not found with this user or course " + enrollmentDto.getUser_id() + "," + enrollmentDto.getCourse_id());
        }
    }

    @Override
    public Enrollment getEnrollmentById(Long userId, Long courseId) {
        User user = new User();
        user.setId(userId);
        Course course = new Course();
        course.setId(courseId);

        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findByUserAndCourse(user, course);

        if (optionalEnrollment.isPresent()) {
            return optionalEnrollment.get();
        }else {
            throw new EntityNotFoundException("Enrollment not found enroll with this user and course");
        }
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll().stream().sorted(Comparator.comparing(Enrollment::getRegisteredDate)).collect(Collectors.toList());
    }

    @Override
    public void deleteEnrollment(Long userId, Long courseId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("Course not found"));

        if (enrollmentRepository.findByUserAndCourse(user, course).isPresent()){
            enrollmentRepository.deleteByUserAndCourse(user, course);
        }else {
            throw new RuntimeException("Deletion failed");
        }
    }
}

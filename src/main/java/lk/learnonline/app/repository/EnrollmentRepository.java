package lk.learnonline.app.repository;

import lk.learnonline.app.entity.Course;
import lk.learnonline.app.entity.Enrollment;
import lk.learnonline.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByUserAndCourse(User user, Course course);

    void deleteByUserAndCourse(User user, Course course);
}

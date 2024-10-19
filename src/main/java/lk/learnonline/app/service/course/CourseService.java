package lk.learnonline.app.service.course;

import lk.learnonline.app.dto.CourseDto;
import lk.learnonline.app.dto.UserDto;
import lk.learnonline.app.entity.Course;
import lk.learnonline.app.entity.User;

import java.util.List;

public interface CourseService {

    Course postCourse(CourseDto courseDto);

    Course updateCourse(Long id, CourseDto courseDto);

    Course getCourseById(Long id);

    List<Course> getAllCourse();

    void deleteCourse(Long id);
}

package lk.learnonline.app.service.course;

import jakarta.persistence.EntityNotFoundException;
import lk.learnonline.app.dto.CourseDto;
import lk.learnonline.app.entity.Course;
import lk.learnonline.app.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;


    public Course saveOrUpdateCourse(Course course, CourseDto courseDto){
        course.setId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setStartdate(courseDto.getStartdate());
        course.setCost(courseDto.getCost());



        return courseRepository.save(course);
    }

    @Override
    public Course postCourse(CourseDto courseDto) {
        return saveOrUpdateCourse(new Course(), courseDto);
    }

    @Override
    public Course updateCourse(Long id, CourseDto courseDto) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()){
            return saveOrUpdateCourse(optionalCourse.get(), courseDto);
        }else {
            throw new EntityNotFoundException("Course not found related to %s".formatted(courseDto.getId()));
        }
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()){
            return optionalCourse.get();
        }else {
            throw new EntityNotFoundException("Course not found related to this id "+id);
        }
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll().stream().sorted(Comparator.comparing(Course::getId)).collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()){
            courseRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Course not found related to this id "+id);
        }
    }
}

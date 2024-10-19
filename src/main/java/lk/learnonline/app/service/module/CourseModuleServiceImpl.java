package lk.learnonline.app.service.module;

import jakarta.persistence.EntityNotFoundException;
import lk.learnonline.app.dto.CourseDto;
import lk.learnonline.app.dto.CourseModuleDto;
import lk.learnonline.app.entity.Course;
import lk.learnonline.app.entity.CourseModule;
import lk.learnonline.app.entity.Topic;
import lk.learnonline.app.repository.CourseModuleRepository;
import lk.learnonline.app.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseModuleServiceImpl implements CourseModuleService{

    private final CourseModuleRepository courseModuleRepository;
    private final CourseRepository courseRepository;

    public CourseModule saveOrUpdateCourseModule(CourseModule courseModule, CourseModuleDto courseModuleDto){
        courseModule.setId(courseModuleDto.getId());
        courseModule.setName(courseModuleDto.getName());

        Course course = courseRepository.findById(courseModuleDto.getCourse_id()).orElseThrow(() ->
                new RuntimeException("Not found the course wit this is" + courseModuleDto.getCourse_id()));
        courseModule.setCourse(course);

//        List<Topic> topicList = courseModuleDto.getTopics() != null ? courseModuleDto.getTopics().stream()
//                .map(topicDto -> new Topic(topicDto.getIdth(), topicDto.getTopicName(), topicDto.getCourseModule()))
//                .collect(Collectors.toList()) : null;
//        courseModule.se

        return courseModuleRepository.save(courseModule);

    }

    @Override
    public CourseModule postCourseModule(CourseModuleDto courseModuleDto) {
        return saveOrUpdateCourseModule(new CourseModule(), courseModuleDto);
    }

    @Override
    public CourseModule updateCourseModule(Long id, CourseModuleDto courseModuleDto) {
        Optional<CourseModule> optionalCourseModule = courseModuleRepository.findById(id);
        if (optionalCourseModule.isPresent()){
            return saveOrUpdateCourseModule(optionalCourseModule.get(), courseModuleDto);
        }else {
            throw new RuntimeException("Course Module not found with this %s".formatted(courseModuleDto.getId()));
        }
    }

    @Override
    public CourseModule getCourseModuleById(Long id) {
        Optional<CourseModule> optionalCourseModule = courseModuleRepository.findById(id);
        if (optionalCourseModule.isPresent()){
            return optionalCourseModule.get();
        }else {
            throw new EntityNotFoundException("Course Module not found with this id "+id);
        }
    }

    @Override
    public List<CourseModule> getAllCourseModule() {
        return courseModuleRepository.findAll().stream()
                .sorted(Comparator.comparing(CourseModule::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCourseModule(Long id) {
        Optional<CourseModule> optionalCourseModule = courseModuleRepository.findById(id);
        if (optionalCourseModule.isPresent()){
            courseModuleRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Course Module not found with this id "+id);
        }
    }
}

package lk.learnonline.app.service.module;

import lk.learnonline.app.dto.CourseModuleDto;
import lk.learnonline.app.entity.CourseModule;

import java.util.List;

public interface CourseModuleService {

    CourseModule postCourseModule(CourseModuleDto CourseModuleDto);

    CourseModule updateCourseModule(Long id, CourseModuleDto CourseModuleDto);

    CourseModule getCourseModuleById(Long id);

    List<CourseModule> getAllCourseModule();

    void deleteCourseModule(Long id);
}

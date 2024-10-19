package lk.learnonline.app.dto;

import jakarta.persistence.*;
import lk.learnonline.app.entity.CourseModule;
import lk.learnonline.app.entity.CourseModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    private Long id;

    private String topicName;

    private Long CourseModule_id;

}

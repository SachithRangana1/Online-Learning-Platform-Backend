package lk.learnonline.app.dto;

import lk.learnonline.app.entity.Topic;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseModuleDto {

    private Long id;
    private String name;

    private Long course_id;





}

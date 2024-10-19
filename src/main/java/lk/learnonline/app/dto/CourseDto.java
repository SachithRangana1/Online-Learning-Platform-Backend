package lk.learnonline.app.dto;

import jakarta.persistence.*;
import lk.learnonline.app.entity.CourseModule;
import lk.learnonline.app.entity.Enrollment;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private Long id;

    private String name;

    private Date startdate;

    private BigDecimal cost;

}

package lk.learnonline.app.dto;

import jakarta.persistence.*;
import lk.learnonline.app.entity.Course;
import lk.learnonline.app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {

    private Long user_id;

    private Long course_id;

    private Date registeredDate;


}

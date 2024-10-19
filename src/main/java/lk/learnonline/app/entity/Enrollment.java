package lk.learnonline.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enrollment")
@IdClass(Enrollment.class)
public class Enrollment implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"courseList", "commentList"})
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"enrollmentList", "courseModuleList"})
    private Course course;

    @Column(name = "registered_date")
    private Date registeredDate;


}

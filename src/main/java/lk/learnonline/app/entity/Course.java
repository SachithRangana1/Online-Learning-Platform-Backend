package lk.learnonline.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = {"enrollmentList", "CourseModuleList"})
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date startdate;

    private BigDecimal cost;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @JsonManagedReference
    private List<CourseModule> CourseModuleList = new ArrayList<>();


    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @JsonManagedReference
    private List<Enrollment> enrollmentList = new ArrayList<>();

    public Course(Long id, String name, Date startdate, BigDecimal cost){
        this.id = id;
        this.name = name;
        this.startdate = startdate;
        this.cost = cost;
    }

    public Course(Long id, String name, Date startdate, BigDecimal cost, List<CourseModule> CourseModuleList, List<Enrollment> enrollmentList){
        if (CourseModuleList != null && !CourseModuleList.isEmpty()){
            CourseModuleList.stream().filter(ml -> ml.getCourse() == null).forEach(ml -> ml.setCourse(this));
        }
        if (CourseModuleList != null && !CourseModuleList.isEmpty()){
            CourseModuleList.forEach(ml -> {
                if (ml.getCourse() != this){
                    throw new IllegalStateException("Another :%s is already associated with another course".formatted(ml.getId()));
                }
            });
        }


        if (enrollmentList != null && !enrollmentList.isEmpty()){
            enrollmentList.stream().filter(el -> el.getCourse() == null).forEach(el -> el.setCourse(this));
            enrollmentList.forEach(el -> {
                if (el.getCourse() != this){
                    throw new IllegalStateException("the course %s is already associate with another".formatted(el.getCourse(), getId()));
                }
            });
        }
        this.id = id;
        this.name = name;
        this.startdate = startdate;
        this.cost = cost;
        this.enrollmentList = enrollmentList;
        this.CourseModuleList = CourseModuleList;
    }

//    @PrePersist
//    public void beforePersist(){
//        if (getEnrollmentList().isEmpty()){
//            throw new IllegalStateException("The course does not have an any course details");
//        }
//        if (getCourseModuleList().isEmpty()){
//            throw new IllegalStateException("The course does not have an any course details");
//        }



}

package lk.learnonline.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = "topicList")
@Table(name = "CourseModule")
public class CourseModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "CourseModule", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Setter(AccessLevel.NONE)
    @JsonManagedReference
    private List<Topic> topicList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonBackReference
    private Course course;


    public CourseModule (Long id, String name){
        this.id = id;
        this.name = name;
    }

    public CourseModule (Long id, String name, List<Topic> topicList, Course course){
        if (topicList != null && !topicList.isEmpty()){
            topicList.stream().filter(tl -> tl.getCourseModule() == null).forEach(ml -> ml.setCourseModule(this));
            topicList.forEach(tl -> {
                if (tl.getCourseModule() != this){
                    throw new IllegalStateException("The CourseModule %s is already associate with another".formatted(tl.getCourseModule(), getId()));
                }
            });
        }
        this.id = id;
        this.name = name;
        this.course = course;
        this.topicList = topicList;
    }
//
//    public void setCourse1(Long id) {
//        this.id = id;
////        this.name = name;
//    }
}

package lk.learnonline.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "topic_name")
    private String topicName;

    @ManyToOne
    @JoinColumn(name = "CourseModule_id", referencedColumnName = "id")
    @JsonIgnore
    private CourseModule CourseModule;

    @OneToMany(mappedBy = "topic", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @Setter(AccessLevel.NONE)
    private List<Comment> commentList = new ArrayList<>();

//    @OneToMany(mappedBy = "topic", cascade = {CascadeType.PERSIST, CascadeType.DETACH})
//    private List<Comment> commentList;
}

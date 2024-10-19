package lk.learnonline.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    private Long id;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "commented_date")
    private Date commentedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @JsonIgnore
    private Topic topic;

}

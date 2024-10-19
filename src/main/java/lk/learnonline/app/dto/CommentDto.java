package lk.learnonline.app.dto;

import jakarta.persistence.*;
import lk.learnonline.app.entity.Topic;
import lk.learnonline.app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private String commentText;
    private Date commentedDate;
    private Long user_id;
    private Long topic_id;

}

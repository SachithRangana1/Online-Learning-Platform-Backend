package lk.learnonline.app.service.comment;

import lk.learnonline.app.dto.CommentDto;
import lk.learnonline.app.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment postComment(CommentDto commentDto);

    Comment updateComment(Long id, CommentDto commentDto);

    Comment getCommentById(Long id);

    List<Comment> getAllComment();

    void deleteComment(Long id);
}

package lk.learnonline.app.service.comment;

import jakarta.persistence.EntityNotFoundException;
import lk.learnonline.app.dto.CommentDto;
import lk.learnonline.app.entity.Comment;
import lk.learnonline.app.entity.Topic;
import lk.learnonline.app.entity.User;
import lk.learnonline.app.repository.CommentRepository;
import lk.learnonline.app.repository.TopicRepository;
import lk.learnonline.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    public Comment SaveOrUpdateComment(Comment comment, CommentDto commentDto){
        comment.setId(commentDto.getId());
        comment.setCommentText(commentDto.getCommentText());
        comment.setCommentedDate(commentDto.getCommentedDate());

        User user = userRepository.findById(commentDto.getUser_id()).orElseThrow(()->{
            throw new RuntimeException("User not found");
        });
        comment.setUser(user);

        Topic topic = topicRepository.findById(commentDto.getTopic_id()).orElseThrow(()->{
            throw new RuntimeException("Topic not found");
        });
        comment.setTopic(topic);

        return commentRepository.save(comment);
    }

    @Override
    public Comment postComment(CommentDto commentDto) {
        return SaveOrUpdateComment(new Comment(), commentDto);
    }

    @Override
    public Comment updateComment(Long id, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()){
            return SaveOrUpdateComment(optionalComment.get(), commentDto);
        }else {
            throw new EntityNotFoundException("Comment not found related to %s".formatted(commentDto.getId()));
        }
    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()){
            return optionalComment.get();
        }else {
            throw new EntityNotFoundException("Comment not found related to this id "+id);
        }
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll().stream().sorted(Comparator.comparing(Comment::getId)).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()){
            commentRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Comment not found related to this id "+id);
        }
    }
}

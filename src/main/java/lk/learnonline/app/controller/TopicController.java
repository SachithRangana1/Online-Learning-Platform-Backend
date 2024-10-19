package lk.learnonline.app.controller;

import jakarta.persistence.EntityNotFoundException;
import lk.learnonline.app.dto.TopicDto;
import lk.learnonline.app.entity.Topic;
import lk.learnonline.app.repository.TopicRepository;
import lk.learnonline.app.service.topic.TopicService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/app/topic")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<?> postTopic (@RequestBody TopicDto topicDto) {
        Topic createdTopic = topicService.postTopic(topicDto);

        if (createdTopic != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTopic);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic (@PathVariable Long id, @RequestBody TopicDto topicDto) {
        try {
            return ResponseEntity.ok(topicService.updateTopic(id, topicDto));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(topicService.getTopicById(id));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic (@PathVariable Long id) {
        try {
            topicService.deleteTopic(id);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Topic not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Something went wrong"));
        }
    }
}

package lk.learnonline.app.service.topic;

import lk.learnonline.app.dto.CourseDto;
import lk.learnonline.app.dto.TopicDto;
import lk.learnonline.app.entity.Course;
import lk.learnonline.app.entity.Topic;

import java.util.List;

public interface TopicService {

    Topic postTopic(TopicDto topicDto);

    Topic updateTopic(Long id, TopicDto topicDto);

    Topic getTopicById(Long id);

    List<Topic> getAllTopics();

    void deleteTopic(Long id);
}

package lk.learnonline.app.service.topic;

import jakarta.persistence.EntityNotFoundException;
import lk.learnonline.app.dto.TopicDto;
import lk.learnonline.app.entity.CourseModule;
import lk.learnonline.app.entity.Topic;
import lk.learnonline.app.repository.CourseModuleRepository;
import lk.learnonline.app.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService{

    private final TopicRepository topicRepository;
    private final CourseModuleRepository courseModuleRepository;

    public Topic saveOrUpdateTopic(Topic topic, TopicDto topicDto){
        topic.setId(topicDto.getId());
        topic.setTopicName(topicDto.getTopicName());

        CourseModule courseModule = courseModuleRepository.findById(topicDto.getCourseModule_id()).orElseThrow(() ->
                new RuntimeException("Module not found with this ".formatted(topicDto.getCourseModule_id())));
        topic.setCourseModule(courseModule);

        return topicRepository.save(topic);

    }

    @Override
    public Topic postTopic(TopicDto topicDto) {
        return saveOrUpdateTopic(new Topic(), topicDto);
    }

    @Override
    public Topic updateTopic(Long id, TopicDto topicDto) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()){
            return saveOrUpdateTopic(optionalTopic.get(), topicDto);
        }else {
            throw new EntityNotFoundException("Topic not found with this %s ".formatted(topicDto.getId()));
        }
    }

    @Override
    public Topic getTopicById(Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()){
            return optionalTopic.get();
        }else {
            throw new EntityNotFoundException("Topic not found with this id "+ id);
        }
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll().stream().sorted(Comparator.comparing(Topic::getId)).collect(Collectors.toList());
    }

    @Override
    public void deleteTopic(Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()){
            topicRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Topic not found with this id "+id);
        }
    }
}

package lk.learnonline.app.service.user;

import jakarta.persistence.EntityNotFoundException;
import lk.learnonline.app.dto.UserDto;
import lk.learnonline.app.entity.User;
import lk.learnonline.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public User saveOrUpdateUser(User user, UserDto userDto){
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setContact(userDto.getContact());
        user.setDob(userDto.getDob());

        return userRepository.save(user);
    }

    @Override
    public User postUser(UserDto userDto) {
        return saveOrUpdateUser(new User(), userDto);
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return saveOrUpdateUser(optionalUser.get(), userDto);
        }else {
            throw new EntityNotFoundException("User not found with this id".formatted(userDto.getId()));
        }
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new EntityNotFoundException("User not found with this id " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("User not found with this id " + id);
        }
    }
}

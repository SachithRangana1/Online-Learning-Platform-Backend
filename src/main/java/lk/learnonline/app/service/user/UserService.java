package lk.learnonline.app.service.user;

import lk.learnonline.app.dto.UserDto;
import lk.learnonline.app.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User postUser(UserDto userDto);

    User updateUser(Long id, UserDto userDto);

    User getUserById(Long id);

    List<User> getAllUsers();

    void deleteUser(Long id);
}

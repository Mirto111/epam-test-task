package ru.epam.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.epam.task.model.User;
import ru.epam.task.repository.UserRepository;

import java.util.List;


@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User user) {
        User u = userRepository.save(user);
        log.info("Пользователь создан='{}'", u);
        return u;
    }

    @Transactional
    public void delete(int userId) {
        if (userRepository.delete(userId) == 0) throw new IllegalArgumentException("Пользователь не найден");
        log.info("Пользователь удален='{}'", userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }
}

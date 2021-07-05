package ru.epam.task.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.epam.task.model.User;
import ru.epam.task.repository.UserRepository;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = {"classpath:schema-postgres.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService service;

    @Autowired
    UserRepository repository;

    @Test
    void create() {
        User user = new User("New User");
        user.setId(service.create(user).getId());
        assertEquals(user, service.get(user.getId()));
    }

    @Test
    void delete() {
        assertNotNull(service.get(1));
        service.delete(1);
        assertNull(service.get(1));
    }

    @Test
    void getAllUsers() {
        
        assertEquals(5, service.getAllUsers().size());
    }

    @Test
    void getAllUsersNotAssignOnProject() {
        assertEquals(3, repository.getAllUsersNotAssignOnProject(3).size());
    }
}
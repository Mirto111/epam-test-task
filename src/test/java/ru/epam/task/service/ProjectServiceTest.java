package ru.epam.task.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.epam.task.model.Project;

import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = {"classpath:schema-postgres.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService service;

    @Test
    void create() {
        Project pr = new Project("New Project");
        pr.setId(service.create(pr).getId());
        assertEquals(pr, service.get(pr.getId()));
    }

    @Test
    void delete() {
        assertNotNull(service.get(1));
        service.delete(1);
        assertNull(service.get(1));
    }

    @Test
    void assignProject() {
        Project pr = service.getProjectWithAllUsers(1);
        assertFalse(pr.getUsers().stream().anyMatch(u -> u.getId() == 5));
        service.assignProject(5, 1);
        Project actual = service.getProjectWithAllUsers(1);
        assertTrue(actual.getUsers().stream().anyMatch(u -> u.getId() == 5));
    }

    @Test
    void getAllProjects() {
        assertEquals(4, service.getAllProjects().size());
    }
}
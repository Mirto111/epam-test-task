package ru.epam.task.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.epam.task.model.SubTask;
import ru.epam.task.model.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = {"classpath:schema-postgres.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest
class TaskServiceTest {

    @Autowired
    TaskService taskService;

    @Autowired
    ProjectService projectService;

    @Test
    void create() {
        Task task = new Task("new Task", "desc", false);
        task.setId(taskService.create(task, 1).getId());
        assertEquals(task, taskService.get(task.getId()));
    }

    @Test
    void delete() {
        assertNotNull(taskService.get(1));
        taskService.delete(1);
        assertNull(taskService.get(1));
    }

    @Test
    void assignUser() {
        Task task = new Task("new Task", "desc", false);
        task.setId(taskService.create(task, 1).getId());
        taskService.assignUser(task.getId(), 1);
        Task tsk = taskService.getTaskWithUser(task.getId());
        assertEquals(tsk.getUser().getId(), 1);
    }

    @Test
    void getAllActiveTasksForUser() {
        assertEquals(2, taskService.getAllActiveTasksForUser(4).size());
    }

    @Test
    void getAllTasksForProject() {
        assertEquals(2, taskService.getAllTasksForProject(1).size());
    }

    @Test
    void getAllTasksForProjectsByUser() {
        assertEquals(3, taskService.getAllTasksForProjectsByUser(List.of(1, 2,3), 1).size());
    }

    @Test
    void closeTask() {
        taskService.closeTask(1);
        assertTrue(taskService.getTaskWithSubTasks(1).getSubTasks().stream().allMatch(SubTask::getClosed));
    }
}
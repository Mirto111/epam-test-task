package ru.epam.task.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.epam.task.model.SubTask;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Sql(scripts = {"classpath:schema-postgres.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest
class SubTaskServiceTest {

    @Autowired
    SubTaskService service;

    @Test
    void createAndAssignOnTask() {
        SubTask sub = new SubTask("Sub1", "test1", false);
        sub.setId(service.createAndAssignOnTask(sub, 2).getId());
        SubTask actual = service.getSubTaskWithTask(sub.getId());
        assertEquals(sub, actual);
        assertEquals(2, actual.getTask().getId());
    }

    @Test
    void createAndAssignOnSubTask() {
        SubTask sub = new SubTask("Sub1", "test1", false);
        sub.setId(service.createAndAssignOnSubTask(sub, 1).getId());
        assertEquals(sub, service.get(sub.getId()));
        assertEquals(1, sub.getSubTasksOf().iterator().next().getId());
    }

    @Test
    void closeSubTask() {
        service.closeSubTask(1);
        List<SubTask> subTasks = service.getAllSubTaskBySubTaskId(1);
        assertTrue(subTasks.stream().allMatch(SubTask::getClosed));
    }

    @Test
    void getAllSubTaskByTask() {
        assertEquals(10, service.getAllSubTaskByTask(1).size());
    }
}
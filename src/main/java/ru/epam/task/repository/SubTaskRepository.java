package ru.epam.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.epam.task.model.SubTask;

import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTask, Integer> {


    @Query("SELECT s FROM SubTask s LEFT JOIN FETCH s.subTasks WHERE s.id=:subTaskId")
    SubTask getSubTaskWithSubTasks(int subTaskId);

    @Query("SELECT DISTINCT s FROM SubTask s LEFT JOIN FETCH s.subTasks WHERE s.task.id=:taskId")
    List<SubTask>getSubTaskByTask(int taskId);

    @Query("SELECT s FROM SubTask s LEFT JOIN FETCH s.task WHERE s.id=:subTaskId")
    SubTask getWithTask(int subTaskId);
}

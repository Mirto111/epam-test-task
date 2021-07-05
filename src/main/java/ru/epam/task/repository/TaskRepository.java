package ru.epam.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.epam.task.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Modifying
    @Query("DELETE FROM Task t WHERE t.id=:taskId")
    int delete(int taskId);

    @Modifying
    @Query("UPDATE Task t SET t.user.id = :userId WHERE t.id = :taskId")
    int assignTaskToUser(Integer taskId, Integer userId);

    @Query("SELECT t FROM Task t WHERE t.user.id=:userId")
    List<Task>getAllActiveTasksForUser(int userId);

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.user WHERE t.project.id=:projectId")
    List<Task>getAllTasksForProject(int projectId);

    @Query("SELECT t FROM Task t WHERE t.project.id IN :projectsIds AND  t.user.id=:userId")
    List<Task>getAllTasksForProjectsByUser(List<Integer> projectsIds, int userId);

    @Query("SELECT t FROM Task t JOIN FETCH t.user  WHERE t.id=:taskId")
    Task getTaskWithUser(int taskId);

    @Query("SELECT t FROM Task t JOIN FETCH t.project  WHERE t.id=:taskId")
    Task getTaskWitProject(int taskId);

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.subTasks  WHERE t.id=:taskId")
    Task getTaskWitSubTasks(int taskId);

    @Query("SELECT t FROM Task t WHERE t.project.id =:projectId AND t.user.id IS NULL")
    List<Task> getAllNotAssignTasksForProject(int projectId);

    @Query("SELECT t FROM Task t WHERE t.project.id=:projectId AND t.closed=false")
    List<Task>getAllNotClosedTasksByProject(int projectId);
}

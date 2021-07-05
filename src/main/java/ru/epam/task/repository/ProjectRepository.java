package ru.epam.task.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.epam.task.model.Project;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

    @Modifying
    @Query("DELETE FROM Project pr WHERE pr.id=:projectId")
    int delete(int projectId);

    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.users WHERE p.id=:projectId")
    Project getProjectWithUsers(int projectId);

    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.tasks WHERE p.id=:projectId")
    Project getProjectWithTasks(int projectId);
}

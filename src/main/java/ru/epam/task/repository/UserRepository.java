package ru.epam.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.epam.task.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:userId")
    int delete(int userId);

    @Query("SELECT u FROM  User u LEFT JOIN FETCH u.projects WHERE u.id=:userId")
    User getUserWithProjects(int userId);

    @Query("SELECT u FROM  User u join u.projects pr where pr.id <>:projectId")
    List<User> getAllUsersNotAssignOnProject(int projectId);
}

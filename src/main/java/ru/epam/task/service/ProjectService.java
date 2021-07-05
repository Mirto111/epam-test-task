package ru.epam.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.epam.task.model.Project;
import ru.epam.task.model.User;
import ru.epam.task.repository.ProjectRepository;
import ru.epam.task.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Project create(Project project) {
        Project pr = projectRepository.save(project);
        log.info("Проект создан='{}'", pr);
        return pr;
    }

    @Transactional
    public void delete(int projectId) {
        if (projectRepository.delete(projectId) == 0) throw new IllegalArgumentException("Проект не найден");
        log.info("Проект удален ='{}'", projectId);
    }

    @Transactional
    public void assignProject(int userId, int projectId) {
        User u = userRepository.getUserWithProjects(userId);
        if (u.getProjects().stream().anyMatch(p -> p.getId() != projectId)) {
            Project pr = projectRepository.getProjectWithUsers(projectId);
            u.addProject(pr);
            userRepository.save(u);
            log.info("Пользователь ='{}' назначен на проект ='{}'", u, pr);
        } else {
            log.info("Пользователь ='{}' уже состоит в проекте ='{}'", userId, projectId);
        }
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project get(int projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    public Project getProjectWithAllUsers(int projectId) {
        return projectRepository.getProjectWithUsers(projectId);
    }
}

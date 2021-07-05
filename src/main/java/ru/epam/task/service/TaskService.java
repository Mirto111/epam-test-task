package ru.epam.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.epam.task.model.Project;
import ru.epam.task.model.Task;
import ru.epam.task.model.User;
import ru.epam.task.repository.ProjectRepository;
import ru.epam.task.repository.SubTaskRepository;
import ru.epam.task.repository.TaskRepository;
import ru.epam.task.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final SubTaskRepository subTaskRepository;

    public TaskService(TaskRepository repository, ProjectRepository projectRepository, UserRepository userRepository, SubTaskRepository subTaskRepository) {
        this.taskRepository = repository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.subTaskRepository = subTaskRepository;
    }

    @Transactional
    public Task create(Task task, int projectId) {
        Project p = projectRepository.findById(projectId).orElse(null);
        if (p != null) {
            task.setProject(p);
            Task t = taskRepository.save(task);
            log.info("Задача создана='{}'", t);
            return t;
        }
        log.info("Задача не создана");
        return null;
    }

    @Transactional
    public void delete(int taskId) {
        if (taskRepository.delete(taskId) == 0) throw new IllegalArgumentException("Задача не найдена");
        log.info("Задача id = '{}' удалена", taskId);
    }

    @Transactional
    public void assignUser(int taskId, int userId) {
        ///проверка что юзер назначен на нужный проект
        User u = userRepository.getUserWithProjects(userId);
        Task t = taskRepository.getTaskWitProject(taskId);
        if (u.getProjects().stream().anyMatch(p-> p.getId().equals(t.getProject().getId()))){
            taskRepository.assignTaskToUser(taskId, userId);
            log.info("Задача id = '{}' назначена пользователю id ='{}'", taskId, userId);
        }
        log.info("Задача id = '{}' не назначена", taskId);
    }

    @Transactional
    public void closeTask(int taskId) {
        Task task = taskRepository.getTaskWitSubTasks(taskId);
        task.getSubTasks().forEach(sub -> sub.setClosed(true));
        subTaskRepository.saveAll(task.getSubTasks());
        log.info("Задача ='{}' и '{}' подзадач закрыты", task, task.getSubTasks().size());
    }

    public List<Task> getAllActiveTasksForUser(int userId) {
        return taskRepository.getAllActiveTasksForUser(userId);
    }

    public List<Task> getAllTasksForProject(int projectId) {
        return taskRepository.getAllTasksForProject(projectId);
    }

    public List<Task> getAllTasksForProjectsByUser(List<Integer> projectsIds, int userId) {

        return taskRepository.getAllTasksForProjectsByUser(projectsIds, userId);
    }

    public List<Task> getAllNotAssignTaskForProject(int projectId) {
        return taskRepository.getAllNotAssignTasksForProject(projectId);
    }

    public List<Task> getAllNotClosedTasksByProject(int projectId) {
        return taskRepository.getAllNotClosedTasksByProject(projectId);
    }

    public Task get(int taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public Task getTaskWithUser(int taskId) {
        return taskRepository.getTaskWithUser(taskId);
    }

    public Task getTaskWithSubTasks(int taskId) {
        return taskRepository.getTaskWitSubTasks(taskId);
    }

}

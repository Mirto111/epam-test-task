package ru.epam.task.logic.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.epam.task.model.Project;
import ru.epam.task.model.Task;
import ru.epam.task.service.ProjectService;
import ru.epam.task.service.TaskService;
import ru.epam.task.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TaskConsole {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    private final Scanner scanner = new Scanner(System.in);

    public TaskConsole(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    public void create() {
        System.out.println("Создание задачи");
        System.out.println("Введите название:");
        String name = scanner.nextLine();
        System.out.println("Введите может описание:");
        String desc = scanner.nextLine();
        System.out.println("Прикрепление задачи к проекту");
        projectService.getAllProjects().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id проекта:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Task t = new Task(name, desc, false);
        taskService.create(t, projectId);
    }

    public void delete() {
        System.out.println("Удаление задачи");
        System.out.println("Список проектов");
        projectService.getAllProjects().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id проекта:");
        int projectId = Integer.parseInt(scanner.nextLine());
        System.out.println("Задачи проекта");
        taskService.getAllTasksForProject(projectId).forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id задачи");
        int taskId = Integer.parseInt(scanner.nextLine());
        taskService.delete(taskId);
    }

    public void assignUser() {
        System.out.println("Назначение задачи пользователю");
        System.out.println("Список пользователей");
        userService.getAllUsers().forEach(u -> System.out.println(u.getId() + " " + u.getName()));
        System.out.println("Введите id пользователя:");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.println("Список проектов");
        projectService.getAllProjects().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id проекта");
        int projectId = Integer.parseInt(scanner.nextLine());
        taskService.getAllNotAssignTaskForProject(projectId).forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id задачи");
        int taskId = Integer.parseInt(scanner.nextLine());
        taskService.assignUser(taskId, userId);
    }

    public void getAllActiveTasksForUser() {
        System.out.println("Список пользователей");
        userService.getAllUsers().forEach(u -> System.out.println(u.getId() + " " + u.getName()));
        System.out.println("Введите id пользователя");
        int userId = Integer.parseInt(scanner.nextLine());
        taskService.getAllActiveTasksForUser(userId).forEach(p -> System.out.println(p.getId() + " " + p.getName()));
    }

    public void getAllTasksForProject() {
        System.out.println("Список проектов");
        projectService.getAllProjects().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id проекта");
        int projectId = Integer.parseInt(scanner.nextLine());
        System.out.println("Все активные задачи проекта");
        taskService.getAllTasksForProject(projectId)
                .forEach(p -> System.out.println(p.getId() + " " + p.getName() + " назначена " + (p.getUser() != null ? p.getUser().getName() : null)));
    }

    public void getAllTasksForProjectsByUser() {
        System.out.println("Список проектов");
        List<Project> projects = projectService.getAllProjects();
        projects.forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id проектов через пробел");
        String strIds = scanner.nextLine();
        List<Integer> ids = Arrays.stream(strIds.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println("Список пользователей");
        userService.getAllUsers().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id пользователя");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.println("Все задачи пользователя для проектов " + ids);
        taskService.getAllTasksForProjectsByUser(ids, userId).forEach(t-> System.out.println(t.getId() + " " + t.getName()));
    }

    public void closeTask() {
        System.out.println("Закрытие задачи");
        System.out.println("Список проектов");
        projectService.getAllProjects().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id проекта");
        int projectId = Integer.parseInt(scanner.nextLine());
        taskService.getAllNotClosedTasksByProject(projectId).forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id задачи");
        int taskId = Integer.parseInt(scanner.nextLine());
        taskService.closeTask(taskId);
    }
}

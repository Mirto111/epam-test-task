package ru.epam.task.logic.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.epam.task.model.Project;
import ru.epam.task.service.ProjectService;
import ru.epam.task.service.UserService;

import java.util.Scanner;


@Slf4j
@Component
public class ProjectConsole {

    private final ProjectService projectService;
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public ProjectConsole(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    public void create() {
        System.out.println("Создание проекта");
        System.out.println("Введите название проекта:");
        String name = scanner.nextLine();
        Project project = new Project(name);
        projectService.create(project);
    }

    public void assignUser() {
        System.out.println("Назначение пользователя на проект");
        projectService.getAllProjects().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id проекта:");
        int projectId = Integer.parseInt(scanner.nextLine());
        userService.getAllUsers().forEach(u -> System.out.println(u.getId() + " " + u.getName()));
        System.out.println("Введите id пользователя:");
        int userId = Integer.parseInt(scanner.nextLine());
        projectService.assignProject(userId, projectId);
    }

    public void delete() {
        System.out.println("Удаление проекта");
        projectService.getAllProjects().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id проекта:");
        int projectId = Integer.parseInt(scanner.nextLine());
        projectService.delete(projectId);
    }

    public void getAllProjects() {
        System.out.println("Список всех проектов");
        projectService.getAllProjects().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
    }

    public void getAllUsersByProject() {
        projectService.getAllProjects().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
        System.out.println("Введите id проекта:");
        int projectId = Integer.parseInt(scanner.nextLine());
        System.out.println("Список всех пользователей проекта");
        projectService.getProjectWithAllUsers(projectId).getUsers().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
    }
}

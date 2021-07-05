package ru.epam.task.logic;

import org.springframework.stereotype.Component;
import ru.epam.task.logic.console.ProjectConsole;
import ru.epam.task.logic.console.TaskConsole;
import ru.epam.task.logic.console.UserConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleTrack {
    // поменять отображение то-стринг объектов
    // передавать сканнер в методы чтобы можно было его закрыть потом
    private final ProjectConsole projectConsole;
    private final TaskConsole taskConsole;
    private final UserConsole userConsole;

    private final Scanner scanner = new Scanner(System.in);

    public ConsoleTrack(ProjectConsole projectConsole, TaskConsole taskConsole, UserConsole userConsole) {
        this.projectConsole = projectConsole;
        this.taskConsole = taskConsole;
        this.userConsole = userConsole;
    }

    public void getCommands() {
        List<String> commands = new ArrayList<>();
        commands.add("1. Проекты");
        commands.add("2. Пользователи");
        commands.add("3. Задачи");
        commands.add("4. Выход");
        commands.forEach(System.out::println);
        System.out.println("Введите  номер команды");
        int num;
        while ((num = Integer.parseInt(scanner.nextLine())) != 4) {
            if (num == 1) {
                getProjects();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 2) {
                getUsers();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 3) {
                getTasks();
                System.out.println();
                commands.forEach(System.out::println);
            } else {
                System.out.println("Неверный номер");
            }
        }
    }

    private void getProjects() {
        List<String> commands = new ArrayList<>();
        commands.add("1. Создать проект");
        commands.add("2. Удалить проект");
        commands.add("3. Назначить пользователя на проект");
        commands.add("4. Показать все проекты");
        commands.add("5. Показать всех пользователей проекта");
        commands.add("6. Выход");
        System.out.println("Проекты");
        commands.forEach(System.out::println);
        System.out.println("Введите  номер команды");
        int num;
        while ((num = Integer.parseInt(scanner.nextLine())) != 6) {
            if (num == 1) {
                projectConsole.create();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 2) {
                projectConsole.delete();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 3) {
                projectConsole.assignUser();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 4) {
                projectConsole.getAllProjects();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 5) {
                projectConsole.getAllUsersByProject();
                System.out.println();
                commands.forEach(System.out::println);

            } else {
                System.out.println("Неверный номер");
            }
        }
    }

    private void getUsers() {
        List<String> commands = new ArrayList<>();
        commands.add("1. Создать пользователя");
        commands.add("2. Удалить пользователя");
        commands.add("3. Показать всех пользователей");
        commands.add("4. Выход");
        System.out.println("Пользователи");
        commands.forEach(System.out::println);
        System.out.println("Введите  номер команды");
        int num;
        while ((num = Integer.parseInt(scanner.nextLine())) != 4) {
            if (num == 1) {
                userConsole.create();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 2) {
                userConsole.delete();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 3) {
                userConsole.getAllUsers();
                System.out.println();
                commands.forEach(System.out::println);
            } else {
                System.out.println("Неверный номер");
            }
        }
    }

    private void getTasks() {
        List<String> commands = new ArrayList<>();
        commands.add("1. Создать задачу");
        commands.add("2. Удалить задачу");
        commands.add("3. Назначить задачу");
        commands.add("4. Закрыть задачу");
        commands.add("5. Показать все активные задачи проекта");
        commands.add("6. Показать все активные задачи пользователя");
        commands.add("7. Показать все активные задачи указанных проектов для конкретного пользователя");
        commands.add("8. Выход");
        System.out.println("Задачи");
        commands.forEach(System.out::println);
        System.out.println("Введите  номер команды");
        int num;
        while ((num = Integer.parseInt(scanner.nextLine())) != 8) {
            if (num == 1) {
                taskConsole.create();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 2) {
                taskConsole.delete();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 3) {
                taskConsole.assignUser();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 5) {
                taskConsole.getAllTasksForProject();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 6) {
                taskConsole.getAllActiveTasksForUser();
                System.out.println();
                commands.forEach(System.out::println);
            } else if (num == 7) {
                taskConsole.getAllTasksForProjectsByUser();
                System.out.println();
                commands.forEach(System.out::println);
            }  else if (num == 4) {
                taskConsole.closeTask();
                System.out.println();
                commands.forEach(System.out::println);
            }
            else {
                System.out.println("Неверный номер");
            }
        }
    }
}

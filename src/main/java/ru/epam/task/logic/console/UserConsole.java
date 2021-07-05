package ru.epam.task.logic.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.epam.task.model.User;
import ru.epam.task.service.UserService;

import java.util.Scanner;

@Slf4j
@Component
public class UserConsole {

    private final UserService service;
    private final Scanner scanner = new Scanner(System.in);

    public UserConsole(UserService service) {
        this.service = service;
    }

    public void create() {
        System.out.println("Создание пользователя");
        System.out.println("Введите имя пользователя:");
        String name = scanner.nextLine();
        User user = new User(name);
        service.create(user);
    }

    public void delete() {
        System.out.println("Удаление пользователя");
        service.getAllUsers().forEach(u -> System.out.println(u.getId() + " " + u.getName()));
        System.out.println("Введите id пользователя:");
        int userId = Integer.parseInt(scanner.nextLine());
        service.delete(userId);
    }

    public void getAllUsers() {
        System.out.println("Список всех пользователей");
        service.getAllUsers().forEach(u -> System.out.println(u.getId() + " " + u.getName()));
    }
}

package ru.epam.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.epam.task.logic.ConsoleTrack;

@SpringBootApplication
public class EpamTestTaskApplication implements CommandLineRunner {

    private final ConsoleTrack consoleTrack;

    public EpamTestTaskApplication(ConsoleTrack consoleTrack) {
        this.consoleTrack = consoleTrack;
    }

    public static void main(String[] args) {
        SpringApplication.run(EpamTestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) {
        consoleTrack.getCommands();
    }
}

package ru.epam.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.epam.task.model.SubTask;
import ru.epam.task.model.Task;
import ru.epam.task.repository.SubTaskRepository;
import ru.epam.task.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubTaskService {

    private final SubTaskRepository subTaskRepository;
    private final TaskRepository taskRepository;

    public SubTaskService(SubTaskRepository subTaskRepository, TaskRepository taskRepository) {
        this.subTaskRepository = subTaskRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public SubTask createAndAssignOnTask(SubTask subTask, int taskId) {
        Task task = taskRepository.getTaskWitSubTasks(taskId);
        subTask.setTask(task);
        task.getSubTasks().add(subTask);
        SubTask sub = subTaskRepository.save(subTask);
        log.info("Подзадача для задачи '{}' создана='{}'", task, sub);
        return sub;
    }

    @Transactional
    public SubTask createAndAssignOnSubTask(SubTask subTask, int subTaskId) {
        SubTask parent = subTaskRepository.getSubTaskWithSubTasks(subTaskId);
        subTask.setTask(parent.getTask());
        parent.addSubTasks(subTask);
        SubTask sub = subTaskRepository.save(subTask);
        log.info("Подзадача для подзадачи '{}' создана='{}'", parent, sub);
        return sub;
    }

    @Transactional
    public int closeSubTask(int subTaskId) {
        List<SubTask> subTasks = new ArrayList<>();
        SubTask subTask = subTaskRepository.getSubTaskWithSubTasks(subTaskId);
        recursive(subTask, subTasks);
        subTasks.forEach(sub -> sub.setClosed(true));
        subTaskRepository.saveAll(subTasks);
        log.info("Подзадач закрыто='{}'", subTasks.size());
        return subTasks.size();
    }

    private void recursive(SubTask subTask, List<SubTask> subTasks) {
        if (subTask.getSubTasks().size() > 0) {
            for (SubTask sub : subTask.getSubTasks()) {
                SubTask s = subTaskRepository.getSubTaskWithSubTasks(sub.getId());
                recursive(s, subTasks);
            }
        }
        subTasks.add(subTask);
    }

    public List<SubTask> getAllSubTaskBySubTaskId(int subTaskId) {
        List<SubTask> subTasks = new ArrayList<>();
        SubTask subTask = subTaskRepository.getSubTaskWithSubTasks(subTaskId);
        recursive(subTask ,subTasks);
        return subTasks.stream().filter(s->s.getId()!=subTaskId).collect(Collectors.toList());
    }

    public SubTask get(int subTaskId) {
        return subTaskRepository.findById(subTaskId).orElse(null);
    }

    public SubTask getSubTaskWithTask(int subTaskId) {
       return subTaskRepository.getWithTask(subTaskId);
    }

    public List<SubTask> getAllSubTaskByTask(int taskId) {
        return subTaskRepository.getSubTaskByTask(taskId);
    }
}

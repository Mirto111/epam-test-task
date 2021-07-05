package ru.epam.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sub_tasks")
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Boolean closed = Boolean.FALSE;
    @ManyToMany
    @JoinTable(name="subtask_subt",
            joinColumns=@JoinColumn(name="subtask_id"),
            inverseJoinColumns=@JoinColumn(name="sustask_sub_id")
    )
    private Set<SubTask> subTasks = new HashSet<>();

    @ManyToMany(mappedBy = "subTasks")
    private Set<SubTask> subTasksOf =new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    public SubTask() {
    }

    public SubTask(String name, String descr, Boolean closed) {
        this.name = name;
        this.description = descr;
        this.closed = closed;
    }

    public void addSubTasks(SubTask subTask) {
        subTasks.add(subTask);
        subTask.getSubTasksOf().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubTask subTask = (SubTask) o;
        return id.equals(subTask.id) &&
                name.equals(subTask.name) &&
                description.equals(subTask.description) &&
                closed.equals(subTask.closed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, closed);
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", closed=" + closed +
                '}';
    }
}

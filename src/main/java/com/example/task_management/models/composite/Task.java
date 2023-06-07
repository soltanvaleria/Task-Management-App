package com.example.task_management.models.composite;
import com.example.task_management.domain.enums.TaskPriority;
import com.example.task_management.models.Prototype;
import com.example.task_management.models.decorator.TaskDecoratorComponent;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Task implements TaskComponent, TaskDecoratorComponent, Prototype<Task> {

    private Integer id;

    private String title;

    private String description;

    private TaskPriority taskPriority;

    private List<TaskComponent> subtaskList;

    private int completedSubtasks;
    private int remainSubtasks;

    public void addSubTask(TaskComponent component) {
        subtaskList.add(component);
    }

    public void removeTask(TaskComponent component) {
        subtaskList.remove(component);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    @Override
    public Task clone() {
        return Task.builder()
                .title(title)
                .description(description)
                .taskPriority(taskPriority)
                .subtaskList(List.copyOf(subtaskList))
                .build();
    }
}

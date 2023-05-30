package com.example.task_management.services;

import com.example.task_management.domain.entities.TaskEty;
import com.example.task_management.domain.enums.TaskPriority;
import com.example.task_management.domain.repository.TaskRepository;
import com.example.task_management.models.composite.SubTask;
import com.example.task_management.models.composite.Task;
import com.example.task_management.models.composite.TaskComponent;
import com.example.task_management.models.decorator.PriorityTaskDecorator;
import com.example.task_management.models.requests.TaskRequestDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private static TaskService INSTANCE;

    private static TaskRepository taskRepository;

    public static TaskService getInstance(TaskRepository repository) {
        if (INSTANCE == null) {
            INSTANCE = new TaskService();
            taskRepository = repository;
        }
        return INSTANCE;
    }

    public void createTask(TaskRequestDto request) {
        var taskEty = TaskEty.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getTaskPriority())
                .build();

        taskRepository.save(taskEty);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::mapTaskEtyToTaskResponse)
                .toList();
    }

    private Task mapTaskEtyToTaskResponse(TaskEty taskEty) {
        List<TaskComponent> subTaskList = taskEty.getSubtaskList().stream()
                .map(entity -> (TaskComponent) SubTask.builder()
                        .id(entity.getId())
                        .title(entity.getTitle())
                        .description(entity.getDescription())
                        .type(entity.getTaskType())
                        .deadline(entity.getDeadline())
                        .build())
                .toList();

        Task task = Task.builder()
                .id(taskEty.getId())
                .title(taskEty.getTitle())
                .description(taskEty.getDescription())
                .subtaskList(subTaskList)
                .build();
        if (taskEty.getPriority() == null) {
            var priorityTaskDecorator = new PriorityTaskDecorator(task);
            priorityTaskDecorator.setPriority(taskEty.getPriority());
        }else {
            task.setTaskPriority(TaskPriority.NULL);
        }

        return task;
    }
}

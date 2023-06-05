package com.example.task_management.services;

import com.example.task_management.domain.entities.TaskEty;
import com.example.task_management.domain.enums.TaskPriority;
import com.example.task_management.domain.repository.SubTaskRepository;
import com.example.task_management.domain.repository.TaskRepository;
import com.example.task_management.models.composite.SubTask;
import com.example.task_management.models.composite.Task;
import com.example.task_management.models.composite.TaskComponent;
import com.example.task_management.models.decorator.PriorityTaskDecorator;
import com.example.task_management.models.filter.DeadlineFilterStrategy;
import com.example.task_management.models.filter.FilterStrategy;
import com.example.task_management.models.filter.TypeFilterStrategy;
import com.example.task_management.models.requests.FilterSubTaskRequest;
import com.example.task_management.models.requests.TaskRequestDto;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private static TaskService INSTANCE;

    private static TaskRepository taskRepository;
    public static SubTaskRepository subTaskRepository;

    private TaskService() {
    }

    public static TaskService getInstance(TaskRepository repository, SubTaskRepository subTaskRepository) {
        if (INSTANCE == null) {
            INSTANCE = new TaskService();
            taskRepository = repository;
            subTaskRepository = subTaskRepository;
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

    public List<SubTask> getFilteredSubTaskList(FilterSubTaskRequest request) {
        FilterStrategy filterStrategy;
        if (request.getDeadline() != null){
            filterStrategy = new DeadlineFilterStrategy(subTaskRepository, request.getDeadline());
        }else if (request.getTaskType() != null){
            filterStrategy = new TypeFilterStrategy(subTaskRepository, request.getTaskType());
        } else {
            return Collections.emptyList();
        }

        return filterStrategy.getFilteredList();
    }
}

package com.example.task_management.services;

import com.example.task_management.domain.entities.SubTaskEty;
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
import com.example.task_management.models.requests.SubTaskRequestDto;
import com.example.task_management.models.requests.TaskRequestDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class TaskService {

    private static TaskService INSTANCE;

    private static TaskRepository taskRepository;

    private static SubTaskRepository subTaskRepository;

    public static TaskService getInstance(TaskRepository repository, SubTaskRepository subTaskRepo) {
        if (INSTANCE == null) {
            INSTANCE = new TaskService();
            taskRepository = repository;
            subTaskRepository = subTaskRepo;
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
        var subTaskDefaultIterator = taskEty.getDefaultSubtaskIterator();
        List<SubTaskEty> subTaskEtyList = new ArrayList<>();
        while (subTaskDefaultIterator.hasNext()) {
            subTaskEtyList.add(subTaskDefaultIterator.getNext());
        }
        List<TaskComponent> subTaskList = subTaskEtyList.stream()
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
        } else {
            task.setTaskPriority(TaskPriority.NULL);
        }

        return task;
    }

    private SubTask mapSubTaskEtyToSubTaskResponse(SubTaskEty subTaskEty) {
        return SubTask.builder()
            .id(subTaskEty.getId())
            .title(subTaskEty.getTitle())
            .description(subTaskEty.getDescription())
            .type(subTaskEty.getTaskType())
            .deadline(subTaskEty.getDeadline())
            .isDone(subTaskEty.getIsDone())
            .build();
    }

    public List<SubTask> getFilteredSubTaskList(FilterSubTaskRequest request) {
        FilterStrategy filterStrategy;
        if (request.getDeadline() != null) {
            filterStrategy = new DeadlineFilterStrategy(subTaskRepository, request.getDeadline());
        } else if (request.getTaskType() != null) {
            filterStrategy = new TypeFilterStrategy(subTaskRepository, request.getTaskType());
        } else {
            return Collections.emptyList();
        }

        return filterStrategy.getFilteredList();
    }

    public List<SubTask> getSubtaskByDeadline(Integer taskId) {
        var taskEty = taskRepository.findById(taskId).orElseThrow();
        var subTaskIterator = taskEty.getDeadlineSubtaskIterator();
        List<SubTask> subTaskList = new ArrayList<>();
        while (subTaskIterator.hasNext()) {
            subTaskList.add(mapSubTaskEtyToSubTaskResponse(subTaskIterator.getNext()));
        }
        return subTaskList;
    }

    public void addSubTask(SubTaskRequestDto request) {
        var taskEty = taskRepository.findById(request.getTaskId())
            .orElseThrow();
        SubTaskEty subTaskEty = SubTaskEty.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .taskType(request.getType())
            .deadline(request.getDeadline())
            .task(taskEty)
            .isDone(false)
            .build();
        subTaskRepository.save(subTaskEty);
    }

    public void editTask(TaskRequestDto request) {
        if (request.getTaskId() == null) {
            throw new IllegalArgumentException("Null task id");
        }
        var taskEty = taskRepository.findById(request.getTaskId()).orElseThrow();
        taskEty.setTitle(request.getTitle());
        taskEty.setPriority(request.getTaskPriority());
        taskEty.setDescription(request.getDescription());
        taskRepository.save(taskEty);
    }

    public void editSubTask(SubTaskRequestDto request) {
        if (request.getSubTaskId() == null) {
            throw new IllegalArgumentException("Null subTask id");
        }
        var subTaskEty = subTaskRepository.findById(request.getSubTaskId()).orElseThrow();
        subTaskEty.setTitle(request.getTitle());
        subTaskEty.setDescription(request.getTitle());
        subTaskEty.setDeadline(request.getDeadline());
        subTaskEty.setTaskType(request.getType());
        subTaskRepository.save(subTaskEty);
    }

    public void completeSubTask(Integer subTaskId) {
        var subTaskEty = subTaskRepository.findById(subTaskId).orElseThrow();
        subTaskEty.setIsDone(true);
        subTaskRepository.save(subTaskEty);
    }

    public void deleteTask(Integer taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        } else {
            throw new IllegalArgumentException(String.format("Task with id: %d does not exists", taskId));
        }
    }

    public void deleteSubTask(Integer subTaskId) {
        if (subTaskRepository.existsById(subTaskId)) {
            subTaskRepository.deleteById(subTaskId);
        } else {
            throw new IllegalArgumentException(String.format("Task with id: %d does not exists", subTaskId));
        }
    }
}


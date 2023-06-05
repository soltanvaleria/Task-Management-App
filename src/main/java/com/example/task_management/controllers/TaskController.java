package com.example.task_management.controllers;

import com.example.task_management.domain.repository.SubTaskRepository;
import com.example.task_management.domain.repository.TaskRepository;
import com.example.task_management.models.composite.SubTask;
import com.example.task_management.models.composite.Task;
import com.example.task_management.models.requests.FilterSubTaskRequest;
import com.example.task_management.models.requests.SubTaskRequestDto;
import com.example.task_management.models.requests.TaskRequestDto;
import com.example.task_management.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskRepository taskRepository, SubTaskRepository subTaskRepository) {
    taskService = TaskService.getInstance(taskRepository, subTaskRepository);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createTask(@RequestBody @Valid TaskRequestDto request){
    taskService.createTask(request);
  }

  @GetMapping
  public List<Task> getAllTasks(){
    return taskService.getAllTasks();
  }

  @GetMapping("/subtasks")
  public List<SubTask> getFilteredSubTasks(@RequestBody @Valid FilterSubTaskRequest request){
    return taskService.getFilteredSubTaskList(request);
  }

  @GetMapping("/subtasks/{taskId}")
  public List<SubTask> getSubTaskByDeadline(@PathVariable Integer taskId) {
    return taskService.getSubtaskByDeadline(taskId);
  }

  @PostMapping("/subtasks")
  @ResponseStatus(HttpStatus.CREATED)
  public void createSubTask(@RequestBody @Valid SubTaskRequestDto request){
    taskService.addSubTask(request);
  }
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void editTask(@RequestBody @Valid TaskRequestDto request) {
    taskService.editTask(request);
  }

  @PutMapping("subtasks")
  @ResponseStatus(HttpStatus.OK)
  public void editSubTask(@RequestBody @Valid SubTaskRequestDto request) {
    taskService.editSubTask(request);
  }

  @PutMapping("subtasks/complete/{subTaskId}")
  @ResponseStatus(HttpStatus.OK)
  public void completeSubTask(@PathVariable Integer subTaskId){
    taskService.completeSubTask(subTaskId);
  }
}

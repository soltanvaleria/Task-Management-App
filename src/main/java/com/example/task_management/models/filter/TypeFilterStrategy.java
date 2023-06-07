package com.example.task_management.models.filter;

import com.example.task_management.domain.entities.SubTaskEty;
import com.example.task_management.domain.enums.TaskType;
import com.example.task_management.domain.repository.SubTaskRepository;
import com.example.task_management.models.composite.SubTask;
import java.util.List;

public class TypeFilterStrategy implements FilterStrategy{

  private final SubTaskRepository repository;
  private TaskType type;

  public TypeFilterStrategy(SubTaskRepository repository, TaskType type) {
    this.repository = repository;
    this.type = type;
  }

  @Override
  public List<SubTask> getFilteredList(Integer taskId) {
    return repository.findAllByTaskType(type).stream()
        .filter(entity -> entity.getTask().getId().equals(taskId))
        .map(this::mapSubTaskEtyToDto)
        .toList();
  }

  private SubTask mapSubTaskEtyToDto(SubTaskEty entity){
    return SubTask.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        .deadline(entity.getDeadline())
        .type(entity.getTaskType())
        .build();
  }
}

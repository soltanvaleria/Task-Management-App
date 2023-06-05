package com.example.task_management.models.filter;

import com.example.task_management.domain.entities.SubTaskEty;
import com.example.task_management.domain.repository.SubTaskRepository;
import com.example.task_management.models.composite.SubTask;
import java.time.LocalDateTime;
import java.util.List;

public class DeadlineFilterStrategy implements FilterStrategy{
  private final SubTaskRepository repository;

  private LocalDateTime deadline;

  public DeadlineFilterStrategy(SubTaskRepository repository, LocalDateTime deadline) {
    this.repository = repository;
    this.deadline = deadline;
  }

  @Override
  public List<SubTask> getFilteredList() {
    return repository.findAll().stream()
        .filter(entity -> entity.getDeadline().isBefore(deadline) || entity.getDeadline().isEqual(deadline))
        .map(this::mapSubTaskEtyToDto)
        .toList();
  }

  private SubTask mapSubTaskEtyToDto(SubTaskEty entity) {
    return SubTask.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        .deadline(entity.getDeadline())
        .build();
  }
}

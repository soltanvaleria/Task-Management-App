package com.example.task_management.domain.repository;

import com.example.task_management.domain.entities.SubTaskEty;
import com.example.task_management.domain.enums.TaskType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepository extends JpaRepository<SubTaskEty, Integer> {
  List<SubTaskEty> findAllByTaskType(TaskType taskType);
}

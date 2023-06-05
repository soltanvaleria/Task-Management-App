package com.example.task_management.models.requests;

import com.example.task_management.domain.enums.TaskType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class FilterSubTaskRequest {

  private TaskType taskType;

  private LocalDateTime deadline;

}

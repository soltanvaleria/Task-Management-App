package com.example.task_management.models.requests;

import com.example.task_management.domain.enums.TaskType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class FilterSubTaskRequest {

  private TaskType taskType;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime deadline;

}

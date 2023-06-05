package com.example.task_management.models.requests;

import com.example.task_management.domain.enums.TaskType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class SubTaskRequestDto {

  private String title;
  private String description;
  private TaskType type;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime deadline;
  @NotNull
  private Integer taskId;
}

package com.example.task_management.models.requests;

import com.example.task_management.domain.enums.TaskType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;


@Data
public class SubTaskRequestDto {


  @Nullable
  private Integer subTaskId;
  @NotBlank
  private String title;
  private String description;
  private TaskType type;
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime deadline;

  @NotNull
  private Integer taskId;
}

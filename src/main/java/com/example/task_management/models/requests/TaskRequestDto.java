package com.example.task_management.models.requests;

import com.example.task_management.domain.enums.TaskPriority;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TaskRequestDto {


    @Nullable
    private Integer taskId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private TaskPriority taskPriority;
}

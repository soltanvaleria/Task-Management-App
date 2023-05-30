package com.example.task_management.models.requests;

import com.example.task_management.domain.enums.TaskPriority;
import lombok.Data;


@Data
public class TaskRequestDto {


    private String title;


    private String description;

    private TaskPriority taskPriority;
}

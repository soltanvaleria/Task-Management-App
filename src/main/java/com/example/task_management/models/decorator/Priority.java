package com.example.task_management.models.decorator;

import com.example.task_management.domain.enums.TaskPriority;

public interface Priority {

    void setPriority(TaskPriority taskPriority);
}

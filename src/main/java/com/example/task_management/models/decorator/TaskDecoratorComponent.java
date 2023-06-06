package com.example.task_management.models.decorator;

import com.example.task_management.domain.enums.TaskPriority;

public interface TaskDecoratorComponent {

    void setPriority(TaskPriority taskPriority);
}

package com.example.task_management.models.decorator;


import com.example.task_management.domain.enums.TaskPriority;

public class PriorityTaskDecorator extends TaskDecorator{

    public PriorityTaskDecorator(Priority priority) {
        super(priority);
    }

    @Override
    public void setPriority(TaskPriority taskPriority) {
        super.setPriority(taskPriority);
    }
}

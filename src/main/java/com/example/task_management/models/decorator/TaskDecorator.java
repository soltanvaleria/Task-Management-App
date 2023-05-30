package com.example.task_management.models.decorator;


import com.example.task_management.domain.enums.TaskPriority;

public class TaskDecorator implements Priority{

    private Priority priority;

    public TaskDecorator(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void setPriority(TaskPriority taskPriority) {
        this.priority.setPriority(taskPriority);
    }
}

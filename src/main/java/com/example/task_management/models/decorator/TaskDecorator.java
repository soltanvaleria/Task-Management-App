package com.example.task_management.models.decorator;


import com.example.task_management.domain.enums.TaskPriority;

abstract public class TaskDecorator implements TaskDecoratorComponent {

    private TaskDecoratorComponent taskDecoratorComponent;

    public TaskDecorator(TaskDecoratorComponent taskDecoratorComponent) {
        this.taskDecoratorComponent = taskDecoratorComponent;
    }

    @Override
    public void setPriority(TaskPriority taskPriority) {
        this.taskDecoratorComponent.setPriority(taskPriority);
    }
}

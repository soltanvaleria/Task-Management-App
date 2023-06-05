package com.example.task_management.domain.iterator;

import com.example.task_management.domain.entities.SubTaskEty;

public interface SubtaskCollection {
  void addSubtask(SubTaskEty subTaskEty);

  SubtaskIterator getDefaultSubtaskIterator();

  SubtaskIterator getDeadlineSubtaskIterator();

}

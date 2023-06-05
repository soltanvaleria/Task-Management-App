package com.example.task_management.domain.iterator;
import com.example.task_management.domain.entities.SubTaskEty;

public interface SubtaskIterator {

  boolean hasNext();

  SubTaskEty getNext();
}

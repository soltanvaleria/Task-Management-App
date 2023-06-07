package com.example.task_management.models.filter;

import com.example.task_management.models.composite.SubTask;
import java.util.List;

public interface FilterStrategy {
  List<SubTask> getFilteredList(Integer taskId);

}

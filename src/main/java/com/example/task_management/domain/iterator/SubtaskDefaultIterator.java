package com.example.task_management.domain.iterator;

import com.example.task_management.domain.entities.SubTaskEty;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SubtaskDefaultIterator implements SubtaskIterator{
  private final List<SubTaskEty> subTaskEtyList;
  private int index;

  public SubtaskDefaultIterator(List<SubTaskEty> subTaskEtyList) {
    this.subTaskEtyList = subTaskEtyList;
  }


  @Override
  public boolean hasNext() {
    return index < subTaskEtyList.size();
  }

  @Override
  public SubTaskEty getNext() {
    var subtask = subTaskEtyList.get(index);
    index++;
    return subtask;
  }


}

package com.example.task_management.domain.iterator;

import com.example.task_management.domain.entities.SubTaskEty;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SubtaskDeadlineIterator implements SubtaskIterator{
  private final List<SubTaskEty> subTaskEtyList;

  private int index;

  public SubtaskDeadlineIterator(List<SubTaskEty> subTaskEtyList) {
    this.subTaskEtyList = new ArrayList<>(subTaskEtyList);
    this.subTaskEtyList.sort(Comparator.comparing(SubTaskEty::getDeadline));
  }

  @Override
  public boolean hasNext() {
    return index < subTaskEtyList.size();
  }

  @Override
  public SubTaskEty getNext() {
    SubTaskEty subTaskEty = subTaskEtyList.get(index);
    index++;
    return subTaskEty;
  }}

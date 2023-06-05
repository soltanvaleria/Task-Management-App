package com.example.task_management.domain.entities;

import com.example.task_management.domain.enums.TaskPriority;
import com.example.task_management.domain.iterator.SubtaskCollection;
import com.example.task_management.domain.iterator.SubtaskDeadlineIterator;
import com.example.task_management.domain.iterator.SubtaskDefaultIterator;
import com.example.task_management.domain.iterator.SubtaskIterator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TaskEty implements SubtaskCollection {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "task_id")
  private Integer id;

  @Column(name = "task_title")
  private String title;

  @Column(name = "task_description")
  private String description;

  @Column(name = "task_priority")
  private TaskPriority priority;

  @Getter(AccessLevel.NONE)
  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
  private List<SubTaskEty> subtaskList;


  @Override
  public void addSubtask(SubTaskEty subTaskEty) {
    this.subtaskList.add(subTaskEty);
  }

  @Override
  public SubtaskIterator getDefaultSubtaskIterator() {
    return new SubtaskDefaultIterator(this.subtaskList);
  }

  @Override
  public SubtaskIterator getDeadlineSubtaskIterator() {
    return new SubtaskDeadlineIterator(this.subtaskList);
  }
}

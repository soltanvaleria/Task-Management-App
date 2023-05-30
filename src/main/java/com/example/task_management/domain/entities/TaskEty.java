package com.example.task_management.domain.entities;

import com.example.task_management.domain.enums.TaskPriority;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TaskEty {

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

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
  private List<SubTaskEty> subtaskList;
}

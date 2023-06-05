package com.example.task_management.domain.entities;
import com.example.task_management.domain.enums.TaskType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subtasks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTaskEty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "subtask_id")
  private Integer id;

  @Column(name = "subtask_title")
  private String title;

  @Column(name = "subtask_description")
  private String description;

  @Column(name = "subtask_type")
  @Enumerated(value = EnumType.STRING)
  private TaskType taskType;

  @Column(name = "subtask_deadline")
  private LocalDateTime deadline;

  @ManyToOne
  @JoinColumn(name = "task_id", referencedColumnName = "task_id")
  private TaskEty task;

  @Column(name = "is_done")
  private Boolean isDone;
}

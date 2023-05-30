package com.example.task_management.domain.repository;


import com.example.task_management.domain.entities.TaskEty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEty, Integer> {

}

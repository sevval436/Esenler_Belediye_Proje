package com.esenler.tasktracking.repository;

import com.esenler.tasktracking.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

package com.esenler.tasktracking.repository;

import com.esenler.tasktracking.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

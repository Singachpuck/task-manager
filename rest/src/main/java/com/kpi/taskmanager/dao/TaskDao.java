package com.kpi.taskmanager.dao;

import com.kpi.taskmanager.model.entities.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface TaskDao {

    Task insert(Task t);

    Task save(Task t);

    Optional<Task> findById(String id);

    List<Task> findAllByOwnerId(Long id);

    void deleteById(String id);
}

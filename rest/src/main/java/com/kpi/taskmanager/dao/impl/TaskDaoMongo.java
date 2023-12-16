package com.kpi.taskmanager.dao.impl;

import com.kpi.taskmanager.dao.TaskDao;
import com.kpi.taskmanager.model.entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDaoMongo extends TaskDao, MongoRepository<Task, String> {
}

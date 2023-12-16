package com.kpi.taskmanager.dao.impl;

import com.kpi.taskmanager.dao.TaskDao;
import com.kpi.taskmanager.model.entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskDaoMongo extends TaskDao, MongoRepository<Task, String> {
}

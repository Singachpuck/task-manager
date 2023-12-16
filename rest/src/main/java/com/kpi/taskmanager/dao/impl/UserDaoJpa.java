package com.kpi.taskmanager.dao.impl;

import com.kpi.taskmanager.dao.UserDao;
import com.kpi.taskmanager.model.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDaoJpa extends UserDao, CrudRepository<User, Long> {
}

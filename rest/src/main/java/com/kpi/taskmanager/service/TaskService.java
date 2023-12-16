package com.kpi.taskmanager.service;

import com.kpi.taskmanager.dao.TaskDao;
import com.kpi.taskmanager.exception.EntityException;
import com.kpi.taskmanager.exception.NotOwnerException;
import com.kpi.taskmanager.mapper.TaskMapper;
import com.kpi.taskmanager.model.dto.TaskDto;
import com.kpi.taskmanager.model.entities.Task;
import com.kpi.taskmanager.model.entities.User;
import com.kpi.taskmanager.service.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskDao taskDao;

    private final UserService userService;

    private final TaskMapper taskMapper;

    private final AuthenticationFacade auth;

    public TaskDto create(TaskDto taskDto) {
        final User currentUser = userService.findByUsername(this.getCurrentUsername());
        if (currentUser == null) {
            throw new EntityException("Authentication is not valid.");
        }

        return taskMapper.taskToDto(taskDao.insert(taskMapper.dtoToTask(taskDto, currentUser.getId())));
    }

    public Task findById(String id) {
        final Optional<Task> task = taskDao.findById(id);
        if (task.isEmpty()) {
            throw new EntityException("Task with id " + id + " does not exist.");
        }
        final User currentUser = userService.findByUsername(this.getCurrentUsername());
        if (!task.get().getOwnerId().equals(currentUser.getId())) {
            throw new NotOwnerException("User " + currentUser.getUsername() + " can not perform this operation.");
        }
        return task.get();
    }

    public Collection<TaskDto> findByOwner(Long ownerId) {
        final User currentUser = userService.findByUsername(this.getCurrentUsername());
        if (currentUser == null) {
            throw new EntityException("Authentication is not valid.");
        }
        if (!Objects.equals(currentUser.getId(), ownerId)) {
            throw new NotOwnerException("Can't get not owned tasks.");
        }

        return taskDao
                .findAllByOwnerId(ownerId)
                .stream()
                .map(taskMapper::taskToDto)
                .toList();
    }

    public TaskDto update(String taskId, TaskDto taskDto) {
        final User currentUser = userService.findByUsername(this.getCurrentUsername());
        if (currentUser == null) {
            throw new EntityException("Authentication is not valid.");
        }
        this.findById(taskId);
        final Task updatedTask = taskMapper.dtoToTask(taskDto, currentUser.getId());
        updatedTask.setId(taskId);
        return taskMapper.taskToDto(taskDao.save(updatedTask));
    }

    public void delete(String taskId) {
        this.findById(taskId);
        taskDao.deleteById(taskId);
    }

    private String getCurrentUsername() {
        return auth.getAuthentication().getName();
    }
}

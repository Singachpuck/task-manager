package com.kpi.taskmanager.controller;

import com.kpi.taskmanager.model.dto.TaskDto;
import com.kpi.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody TaskDto taskDto) {
        final TaskDto created = taskService.create(taskDto);

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{taskId}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .build();
    }

    @GetMapping("/users/{ownerId}")
    ResponseEntity<?> getByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(taskService.findByOwner(ownerId));
    }

    @GetMapping("/{taskId}")
    ResponseEntity<?> get(@PathVariable String taskId) {
        return ResponseEntity.ok(taskService.findById(taskId));
    }

    @PutMapping("/{taskId}")
    ResponseEntity<?> update(@PathVariable String taskId, @Valid @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.update(taskId, taskDto));
    }

    @DeleteMapping("/{taskId}")
    ResponseEntity<?> delete(@PathVariable String taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}

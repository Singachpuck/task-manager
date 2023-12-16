package com.kpi.taskmanager.mapper;

import com.kpi.taskmanager.model.auth.Authorities;
import com.kpi.taskmanager.model.dto.TaskDto;
import com.kpi.taskmanager.model.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", expression = "java(owner)")
    Task dtoToTask(TaskDto dto, Long owner);

    TaskDto taskToDto(Task task);
}

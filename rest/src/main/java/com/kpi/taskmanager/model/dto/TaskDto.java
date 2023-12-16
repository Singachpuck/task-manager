package com.kpi.taskmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long ownerId;
}

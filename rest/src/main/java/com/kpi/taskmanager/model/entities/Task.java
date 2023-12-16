package com.kpi.taskmanager.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Task {

    @Id
    private String id;

    private String title;

    private String description;

    private Long ownerId;
}

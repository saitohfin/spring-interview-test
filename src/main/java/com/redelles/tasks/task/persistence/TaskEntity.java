package com.redelles.tasks.task.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.redelles.tasks.user.persistence.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "tasks")
@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;

    private String title;

    private String body;

    @ManyToOne
    @JoinColumn(name = "createdBy", nullable = false)
    private UserEntity createdBy;
}

package com.redelles.tasks.task;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.redelles.tasks.exceptions.NotFoundException;
import com.redelles.tasks.task.domain.Task;
import com.redelles.tasks.task.persistence.TaskPersistence;

@Service
public class TaskService {

    private final TaskPersistence persistence;

    public TaskService(final TaskPersistence persistence) {
        this.persistence = persistence;
    }

    public Collection<Task> getTasks(final String username) {
        return this.persistence.findAllByUser(username);
    }

    public Task getTask(final Long id, final String username) throws NotFoundException {
        return this.persistence.getTask(id, username)
            .orElseThrow(() -> new NotFoundException("Not found archived task with id : " + id));
    }

    @Transactional
    public Task create(final Task task) {
        return this.persistence.createTask(task);
    }

    public Task update(final Task task) throws NotFoundException {
        this.getTask(task.id(), task.createdBy());
        return this.persistence.update(task);
    }
}

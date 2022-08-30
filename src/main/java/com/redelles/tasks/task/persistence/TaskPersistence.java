package com.redelles.tasks.task.persistence;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.redelles.tasks.task.domain.Task;
import com.redelles.tasks.user.persistence.UserRepository;

@Component
public class TaskPersistence {

    private final TaskRepository repository;
    private final UserRepository userRepository;

    public TaskPersistence(final TaskRepository repository, final UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Collection<Task> findAllByUser(final String username) {
        return this.repository.findAllByUser(username)
            .stream().map(this::toDomain).toList();
    }

    public Task createTask(final Task task) {
        final TaskEntity entity = new TaskEntity();
        entity.setTitle(task.title());
        entity.setBody(task.body());
        entity.setCreatedBy(this.userRepository.findByUsername(task.createdBy()));
        final TaskEntity created = this.repository.save(entity);
        return this.toDomain(created);
    }

    public Task update(final Task task) {
        final TaskEntity entity = this.repository.findById(task.id()).orElseGet(() -> {
            final TaskEntity newEntity = new TaskEntity();
            newEntity.setCreatedBy(this.userRepository.findByUsername(task.createdBy()));
            return newEntity;
        });
        entity.setTitle(task.title());
        entity.setBody(task.body());

        final TaskEntity updated = this.repository.save(entity);
        return this.toDomain(updated);
    }

    private Task toDomain(final TaskEntity entity) {
        final Task task = new Task(entity.getIdTask(), entity.getCreatedBy().getUsername());
        task.title(entity.getTitle());
        task.body(entity.getBody());
        return task;
    }

    public Optional<Task> getTask(final Long id, final String username) {
        final Optional<TaskEntity> opt = this.repository.findTask(id, username);
        return opt.map(this::toDomain);
    }
}

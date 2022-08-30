package com.redelles.tasks.task.persistence;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

    @Query(value = "SELECT  task FROM TaskEntity task " +
        "WHERE task.createdBy.username = :username "
    )
    Collection<TaskEntity> findAllByUser(@Param("username") final String username);

    @Query(value = "SELECT  task FROM TaskEntity task " +
        "WHERE task.idTask = :id and task.createdBy.username = :username")
    Optional<TaskEntity> findTask(Long id, String username);
}

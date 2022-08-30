package com.redelles.tasks.task.api;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redelles.tasks.TasksApplication;
import com.redelles.tasks.exceptions.NotFoundException;
import com.redelles.tasks.task.TaskService;
import com.redelles.tasks.task.domain.Task;

@RestController
@RequestMapping(TasksApplication.API_VERSION)
public class TaskController implements TaskEndpoint {

    private final TaskService service;

    public TaskController(final TaskService service) {
        this.service = service;
    }

    @Override
    public Collection<TaskDTO> getAllTasks() {
        final String username = this.getUsername();
        final Collection<Task> tasks = this.service.getTasks(username);
        return tasks.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTask(@PathVariable("id") final Long id) throws NotFoundException {
        final String username = this.getUsername();
        final Task task = this.service.getTask(id, username);
        return this.toDTO(task);
    }

    @Override
    public TaskDTO createTask(@RequestBody final CreateTaskDTO dto) {
        if (StringUtils.isBlank(dto.getTitle()) && StringUtils.isNoneBlank(dto.getBody())) {
            dto.setTitle(dto.getBody().substring(0, 20));
        }

        final Task newTask = new Task(dto.getTitle(), dto.getBody(), this.getUsername());
        final Task save = this.service.create(newTask);
        return this.toDTO(save);
    }

    @Override
    public TaskDTO updateTask(@RequestBody final TaskDTO dto) throws NotFoundException {
        final String username = this.getUsername();
        final Task toUpdate = this.toTask(dto, username);
        final Task updated = this.service.update(toUpdate);
        return this.toDTO(updated);
    }

    private TaskDTO toDTO(final Task task) {
        return TaskDTO.builder()
            .id(task.id())
            .title(task.title())
            .body(task.body())
            .build();
    }

    private Task toTask(final TaskDTO dto, final String username) {
        final Task task = new Task(dto.getId(), username);
        task.body(dto.getBody());
        task.title(dto.getTitle());
        return task;
    }

    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

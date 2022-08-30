package com.redelles.tasks.task.api;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.redelles.tasks.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tasks", description = "Tasks, Notes, Ideas")
public interface TaskEndpoint {

    String TASKS_URL = "/tasks";

    @Operation(summary = "Get all tasks for an user", responses = {
        @ApiResponse(description = "Successful Operation", responseCode = "200",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = TaskDTO.class))),
    })
    @GetMapping(TASKS_URL)
    Collection<TaskDTO> getAllTasks();

    @Operation(summary = "Get a task for his id", responses = {
        @ApiResponse(description = "Successful Operation", responseCode = "200",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = TaskDTO.class))),
    })
    @GetMapping(TASKS_URL + "/{id}")
    TaskDTO getTask(@PathVariable("id") Long id) throws NotFoundException;

    @Operation(summary = "Create a new tasks for an user", responses = {
        @ApiResponse(description = "Successful Operation", responseCode = "200",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = TaskDTO.class))),
    })
    @PostMapping(TASKS_URL)
    @ResponseStatus(HttpStatus.CREATED)
    TaskDTO createTask(@RequestBody CreateTaskDTO dto);

    @Operation(summary = "Update an existing task", responses = {
        @ApiResponse(description = "Successful Operation", responseCode = "200",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = TaskDTO.class))),
    })
    @PutMapping(TASKS_URL)
    TaskDTO updateTask(@RequestBody TaskDTO dto) throws NotFoundException;
}

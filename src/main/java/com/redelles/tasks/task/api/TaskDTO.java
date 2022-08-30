package com.redelles.tasks.task.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TaskDTO {

    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String body;

}

package com.redelles.tasks.user.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Entity
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    private String username;
    @NotNull
    private String password;

    public UserEntity(@NotNull final String username, @NotNull final String password) {
        this.username = username;
        this.password = password;
    }
}

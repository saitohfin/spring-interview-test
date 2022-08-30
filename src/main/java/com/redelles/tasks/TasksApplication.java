package com.redelles.tasks;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.redelles.tasks.task.TaskService;
import com.redelles.tasks.task.domain.Task;
import com.redelles.tasks.user.persistence.UserEntity;
import com.redelles.tasks.user.persistence.UserRepository;

@SpringBootApplication
public class TasksApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public static final String API_VERSION = "v1";

    public static void main(final String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }

    @PostConstruct
    public void initialize() {
        final String password = this.passwordEncoder.encode("user");
        final List<UserEntity> users = Arrays.asList(
            new UserEntity("user1", password),
            new UserEntity("user2", password),
            new UserEntity("user3", password),
            new UserEntity("user4", password)
        );
        this.userRepository.saveAll(users);
        Arrays.asList(
            new Task("task1", "dummyText1", "user1"),
            new Task("task12", "dummyText12", "user1"),
            new Task("task2", "dummyText2", "user2"),
            new Task("task5", "dummyText3", "user3"),
            new Task("task6", "dummyText4", "user4")
        ).forEach(this.taskService::create);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

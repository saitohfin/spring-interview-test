package com.redelles.tasks.task.domain;

public class Task {

    private final Long id;
    private final String createdBy;
    private String title;
    private String body;

    public Task(final Long id, final String createdBy) {
        this.id = id;
        this.createdBy = createdBy;
    }

    public Task(final String title, final String createdBy) {
        this.id = null;
        this.title = title;
        this.createdBy = createdBy;
    }

    public Task(final String title, final String body, final String createdBy) {
        this.id = null;
        this.title = title;
        this.body = body;
        this.createdBy = createdBy;
    }

    public Long id() {
        return this.id;
    }

    public String createdBy() {
        return this.createdBy;
    }

    public String title() {
        return this.title;
    }

    public void title(final String newTitle) {
        this.title = newTitle;
    }

    public String body() {
        return this.body;
    }

    public void body(final String body) {
        this.body = body;
    }
}

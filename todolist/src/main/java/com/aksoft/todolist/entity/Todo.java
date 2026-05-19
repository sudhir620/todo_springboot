package com.aksoft.todolist.entity;

import jakarta.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskName;
    private String taskDescription;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public Todo() {
    }

    public Todo(Long id, String taskName, String taskDescription, TaskStatus taskStatus) {
        this(taskName, taskDescription, taskStatus);
        this.id = id;
    }

    public Todo(String taskName, String taskDescription, TaskStatus taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public enum TaskStatus{
        PENDING,
        COMPLETED
    }
}

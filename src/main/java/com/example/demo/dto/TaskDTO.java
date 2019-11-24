package com.example.demo.dto;

import com.example.demo.model.User;

import java.util.Set;

public class TaskDTO {

    private int id;
    private String task;
    private Set<User> users;

    public TaskDTO(String task, Set<User> users) {
        this.task = task;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", user='" + users +
                '}';
    }
}

package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "task")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private int id;
    @Column(name = "task")
    private String task;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_task", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<User> users;

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
}

package com.tarefas.tarefas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //varias tarefas para um usuario
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name= "user_id", nullable = false, updatable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(length = 255, nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;

    public Task() {
    }

    public Task(Long id, User user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public @NotNull User getUser() {
        return user;
    }

    public Task setUser(@NotNull User user) {
        this.user = user;
        return this;
    }

    public @NotBlank @Size(min = 1, max = 255) String getDescription() {
        return description;
    }

    public Task setDescription(@NotBlank @Size(min = 1, max = 255) String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

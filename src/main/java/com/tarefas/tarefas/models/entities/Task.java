package com.tarefas.tarefas.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //varias tarefas para um usuario
//    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name= "user_id", nullable = false, updatable = false)
    private User user;

    @Column(length = 255, nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;

}

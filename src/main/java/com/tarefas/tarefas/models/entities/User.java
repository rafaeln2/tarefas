package com.tarefas.tarefas.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {
    public interface CreateUser {
    }

    public interface UpdateUser {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class) //@NotBlank and @NotNull @NotEmpty all do the same stuff, using just for practice
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 2, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 20, nullable = false)
    @NotBlank(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = CreateUser.class, min = 6, max = 50)
    private String password;

    @OneToMany(mappedBy = "user") //nome da variavel de usuario(essa classe) dentro da clase task
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Task> tasks;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "address_id", nullable = true, updatable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Address address;

}

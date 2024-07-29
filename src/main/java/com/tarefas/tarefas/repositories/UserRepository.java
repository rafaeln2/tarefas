package com.tarefas.tarefas.repositories;

import com.tarefas.tarefas.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
}

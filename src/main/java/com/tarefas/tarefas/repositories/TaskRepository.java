package com.tarefas.tarefas.repositories;

import com.tarefas.tarefas.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
//TODO: implementar manualmente tanto conexão quanto criação dos sql usando prepareStatement e driver
    List<Task> findByUser_Id(Long id); // underline separa os atributos da classe

//    Optional<Task> findById(Long id);

//    @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id") //jpql
//    List<Task> findByUserId(@Param("id") Long id);

//    @Query(nativeQuery = true, value = "SELECT * FROM public.task t WHERE t.user_id = :id") //jpql
//    List<Task> findByUserId(@Param("id") Long id);
}

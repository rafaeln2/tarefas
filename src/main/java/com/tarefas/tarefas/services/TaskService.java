package com.tarefas.tarefas.services;

import com.tarefas.tarefas.models.entities.Task;
import com.tarefas.tarefas.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskRepository taskRepository;

    public Task findById(final Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
                "Tarefa não encontrado: %s".formatted(id))
        );
    }

    public List<Task> findAllByUserId(final Long userId){
        List<Task> tasks = taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task task){
        if (task.getId() != null){
            if (taskRepository.findById(task.getId()).isPresent()) {
                throw new RuntimeException("Task já existe no banco com Id: %s".formatted(task.getId()));
            }
            task.setId(null);
        }
        task.setUser(userService.findById(task.getUser().getId()));
        return taskRepository.save(task);
    }

    @Transactional
    public Task update(Task task){
        var taskDB = this.findById(task.getId());
        taskDB.setDescription(task.getDescription());
        return taskRepository.save(taskDB);
    }

    @Transactional
    public void delete(Long id){
        final var taskDB = findById(id);
        try {
            taskRepository.delete(taskDB);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há outras entidades relacionadas");
        }
    }
}

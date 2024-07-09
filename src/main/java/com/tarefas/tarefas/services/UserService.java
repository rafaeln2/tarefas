package com.tarefas.tarefas.services;

import com.tarefas.tarefas.models.User;
import com.tarefas.tarefas.repositories.TaskRepository;
import com.tarefas.tarefas.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(final Long id){
        Optional<User> usuario = userRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado: %s".formatted(id))
        );
    }

    @Transactional
    public User create(User user){
        //se já existe id no banco ele joga exception avisando, se não existe ele setta o Id como nulo e continua a criação
        if (user.getId() != null){
            if (userRepository.findById(user.getId()).isPresent()) {
                throw new RuntimeException("Usuário já existe no banco com Id: %s".formatted(user.getId()));
            }
            user.setId(null);
        }
        user = userRepository.save(user);
        return user;
    }

    @Transactional
    public User update(User user){
        var userDB = findById(user.getId());
        userDB.setPassword(user.getPassword());
        return userRepository.save(userDB);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            //TODO: mudar pra cascade
            throw new RuntimeException("Não é possivel excluir pois há outras entidades relacionadas");
        }
    }
}

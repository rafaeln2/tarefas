package com.tarefas.tarefas.services;

import com.tarefas.tarefas.models.entities.User;
import com.tarefas.tarefas.repositories.UserRepository;
import com.tarefas.tarefas.services.exceptions.DataBindingViolationException;
import com.tarefas.tarefas.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ViaCepService viaCepService;

    public User findById(final Long id){
        Optional<User> usuario = userRepository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException(
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
    public User update(User user, String cep){
        var userDB = findById(user.getId());
        userDB.setPassword(user.getPassword());
        if (Strings.isNotEmpty(cep)){
            userDB.setAddress(viaCepService.findByCep(cep));
        }
        return userRepository.save(userDB);
    }

//    @Transactional()
    /*
    transactional quebra controle de exceção pelo try, sou obrigado a chamar this.userRepository.flush(); depois do Delete pra executar no DB e entrar no catch se não a anotação lida com o erro antes,
        sendo assim impossivel tratar a exceção dentro do metodo.
    aparenta não dar problema quando exceção é jogada de uma função anonima e.g o método de findById dessa classe
    https://stackoverflow.com/a/64658890
     */
    public void delete(Long id){
        final var userDB = findById(id);
        try {
            this.userRepository.delete(userDB);

        } catch (Exception e) {
            throw new DataBindingViolationException("Não é possivel excluir pois há outras entidades relacionadas");
        }
    }
}

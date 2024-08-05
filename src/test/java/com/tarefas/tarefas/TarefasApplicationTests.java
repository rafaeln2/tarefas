package com.tarefas.tarefas;

import com.tarefas.tarefas.models.entities.Address;
import com.tarefas.tarefas.models.entities.User;
import com.tarefas.tarefas.repositories.TaskRepository;
import com.tarefas.tarefas.repositories.UserRepository;
import com.tarefas.tarefas.services.ViaCepService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TarefasApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Test
    void contextLoads() {
    }

    @Test
    void checaConexao() throws SQLException {
        Assertions.assertTrue(dataSource != null);
        Assertions.assertTrue(dataSource.getConnection() != null);
    }

    @Test
    void validaBuscaBancoNaUnha() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try {
                conn.setAutoCommit(false);
                final var sql = """
                        SELECT u.id id, u.password password, u.username username FROM public.users u
                        """;
                try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                    try (ResultSet rs = pstm.executeQuery()) {
                        List<User> users = new ArrayList<>();
                        if (rs.next()) {
                            final var user = new User();
                            user.setId(rs.getLong("id"));
                            user.setPassword(rs.getString("password"));
                            user.setUsername(rs.getString("username"));
                            users.add(user);
                        }
                        Assertions.assertFalse(users.isEmpty());
                    }
                }
            } finally {
                conn.setAutoCommit(true);
                conn.commit();
            }
        }
//		pstm.setInt();

    }

    @Test
    void validaBuscaBancoJPARepository() throws SQLException {
        final var primeiroUsuario = userRepository.findById(1L).get();
        Assertions.assertTrue(primeiroUsuario != null ? !taskRepository.findByUser_Id(primeiroUsuario.getId()).isEmpty() : false);

    }

    @Test
    void testaRequisitaçãoViaCep() {
        try {
            Address cepExistente = viaCepService.findByCep("88117260");
            System.out.println(cepExistente.toString());
//            Address cepNaoExistente = viaCepService.findByCep("88117269");
            Assertions.assertTrue(cepExistente != null);
//            Assertions.assertTrue(cepNaoExistente == null);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void validaRequisicaoViaCep() {
        final var cep = "88117260";
        try {
            final var endereco = viaCepService.findByCep(cep);
            System.out.println(endereco.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    void testeDataBindingViolationException() {
//
//        try {
//            final var endereco = viaCepService.findByCep(cep);
//            System.out.println(endereco.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}

package com.tarefas.tarefas;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import com.tarefas.tarefas.models.User;
import com.tarefas.tarefas.repositories.TaskRepository;
import com.tarefas.tarefas.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.vendor.Database;

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
		final Connection conn = dataSource.getConnection();
		final var sql = """
				SELECT u.id id, u.password password, u.username username FROM public.users u
				""";
		try (PreparedStatement pstm = conn.prepareStatement(sql)){
			try (ResultSet rs = pstm.executeQuery()){
				List<User> users = new ArrayList<>();
				if (rs.next()){
					final var user = new User();
					user.setId(rs.getLong("id"));
					user.setPassword(rs.getString("password"));
					user.setUsername(rs.getString("username"));
					users.add(user);
				}
				Assertions.assertFalse(users.isEmpty());
			}
		}
//		pstm.setInt();

	}

	@Test
	void validaBuscaBancoJPARepository() throws SQLException {
		final var primeiroUsuario = userRepository.findById(1L).get();
		Assertions.assertTrue(primeiroUsuario != null ? !taskRepository.findByUser_Id(primeiroUsuario.getId()).isEmpty() : false);

	}

}

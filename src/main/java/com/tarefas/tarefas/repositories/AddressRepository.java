package com.tarefas.tarefas.repositories;

import com.tarefas.tarefas.models.Address;
import com.tarefas.tarefas.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

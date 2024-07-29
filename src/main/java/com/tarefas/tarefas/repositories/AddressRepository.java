package com.tarefas.tarefas.repositories;

import com.tarefas.tarefas.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

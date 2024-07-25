package com.tarefas.tarefas.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarefas.tarefas.models.Address;
import com.tarefas.tarefas.models.dtos.AddressDTO;
import com.tarefas.tarefas.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address findById(final Long id){
        final var obj = addressRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException(
                "Endereco não encontrado: %s".formatted(id))
        );
    }

    @Transactional
    public Address create(Address Address){
        if (Address.getId() != null){
            if (addressRepository.findById(Address.getId()).isPresent()) {
                throw new RuntimeException("Endereco já existe no banco com Id: %s".formatted(Address.getId()));
            }
            Address.setId(null);
        }
        return addressRepository.save(Address);
    }

    @Transactional
    public Address update(Address Address){
//        var obj = this.findById(Address.getId());
//        obj.setDescription(Address.getDescription());
        return addressRepository.save(Address);
    }

    @Transactional
    public void delete(Long id){
        final var obj = findById(id);
        try {
            addressRepository.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há outras entidades relacionadas");
        }
    }
}

package com.tarefas.tarefas.services;

import com.tarefas.tarefas.models.dtos.AddressRecordDTO;
import com.tarefas.tarefas.models.entities.Address;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ViaCepService {
    public static final String VIA_CEP = "http://viacep.com.br/ws/";
    public static final String JSON = "/json/";
//    @Autowired
//    private RestTemplate restTemplate;

    public Address findByCep(final String cep) {
        try {
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(VIA_CEP + cep + JSON)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final ObjectMapper mapper = new ObjectMapper();
            if (response.body().contains("erro")){
                throw new IllegalArgumentException("CEP não encontrado!");
            }
            final var dto = mapper.readValue(response.body(), AddressRecordDTO.class);
            final var entity = new Address(dto.cep(), dto.logradouro(), dto.complemento(), dto.bairro(), dto.uf());
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

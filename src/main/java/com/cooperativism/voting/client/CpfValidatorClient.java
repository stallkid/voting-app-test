package com.cooperativism.voting.client;

import com.cooperativism.voting.client.response.CpfValidatorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CpfValidatorClient {

    @Value("${client.service.cpf}")
    private String cpfValidatorClientHost;

    public CpfValidatorResponse validateCpf(final String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(cpfValidatorClientHost + cpf, CpfValidatorResponse.class);
    }
}

package com.example.consumirapi.persistencia.agenteServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class TokenAccessService implements ITokenAccessService {

    private final String _uri = "https://login.microsoftonline.com/92cf6300-1c93-4b01-9a58-9603b66b404d/oauth2/v2.0/token";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String obtenerToken() {

        String Client_Id = "acb58f0b-2153-4d9f-8ebf-2bf811fb1ecb";//Cliente
        String Scope = "api://daab0055-e4ef-4e17-8f46-6eeb3a2d4c4d/.default";//Grap Servidor
        String Client_Secret = "EtS8Q~WYzQosTXd3vD1sEJb0P~gH25Zk3kn47bet"; //Cliente
        String Grant_Type = "client_credentials";

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", Client_Id);
        map.add("scope", Scope);
        map.add("client_secret", Client_Secret);
        map.add("grant_type", Grant_Type);

//        Map<String, String> map = new HashMap<>();
//        map.put("client_id", Client_Id);
//        map.put("scope", Scope);
//        map.put("client_secret", Client_Secret);
//        map.put("grant_type", Grant_Type);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        //HttpEntity<Map<String, String>> request = new HttpEntity<>(map,headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(_uri, request, String.class);

        return response.getBody();
    }
}

package com.abstergo.fharmacia.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void cadastrarEListarCliente() throws Exception {
        var json = "{" +
                "\"nome\":\"João Silva\"," +
                "\"email\":\"joao@example.com\"," +
                "\"cpf\":\"12345678909\"," +
                "\"telefone\":\"11999999999\"," +
                "\"endereco\":{\"logradouro\":\"Rua A\",\"numero\":\"123\",\"complemento\":\"\",\"bairro\":\"Centro\",\"cidade\":\"Cidade\",\"uf\":\"SP\",\"cep\":\"01000-000\"}}";

        mvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("João Silva"));

        mvc.perform(get("/clientes")).andExpect(status().isOk()).andExpect(jsonPath("$[0].nome").value("João Silva"));
    }
}

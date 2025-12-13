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
class ProdutoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void cadastrarEListarProduto() throws Exception {
        var json = "{" +
                "\"nome\":\"Remédio X\"," +
                "\"descricao\":\"Para dor\"," +
                "\"preco\":10.50," +
                "\"fabricante\":\"Lab\",\"quantidadeEstoque\":100}";

        mvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Remédio X"));

        mvc.perform(get("/produtos")).andExpect(status().isOk()).andExpect(jsonPath("$[0].nome").value("Remédio X"));
    }
}

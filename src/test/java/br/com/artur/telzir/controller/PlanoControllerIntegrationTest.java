package br.com.artur.telzir.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers(disabledWithoutDocker = true)
@DisplayName("Teste de Integracao Plano Controller")
public class PlanoControllerIntegrationTest {


  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {

  }

  @Nested
  @DisplayName("testes endPoint findAll")
  class findALL {

    @Test
    @DisplayName("testando o sucesso do metodo")
    public void findAll() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("api/plano/")
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());


    }

  }

}


package br.com.artur.telzir.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import br.com.artur.TelzirContainer;
import br.com.artur.telzir.domain.dto.PlanoOutPutDto;
import br.com.artur.telzir.domain.dto.ValorPorMinutoDto;
import br.com.artur.telzir.domain.dto.ValorPorMinutoOutPutDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers(disabledWithoutDocker = true)
@DisplayName("Teste de Integracao Plano Controller")
public class PlanoControllerIntegrationTest {


  private Map<String, String> headers;

  @Autowired
  private ObjectMapper objectMapper;

  public static final String ENV_DISABLE_TEST_CONTAIENRS = "DISABLE_TEST_CONTAIENRS";

  @LocalServerPort
  private Integer port;

  @Container
  public static TelzirContainer container = TelzirContainer
      .getInstance(StringUtils.isBlank(System.getenv(ENV_DISABLE_TEST_CONTAIENRS)));

  static {
    container.start();
  }

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    headers = Map.of(
        "Content-Type", "application/json");
  }

  @Nested
  @DisplayName("testes endPoint findAll")
  class findALL {

    @Test
    @DisplayName("testando o sucesso do metodo")
    public void findAll() throws Exception {

      PlanoOutPutDto plano30 = PlanoOutPutDto.builder()
          .id(2L)
          .nome("Plano fale mais 30")
          .build();

      PlanoOutPutDto plano60 = PlanoOutPutDto.builder()
          .id(3L)
          .nome("Plano fale mais 60")
          .build();
      PlanoOutPutDto plano120 = PlanoOutPutDto.builder()
          .id(4L)
          .nome("Plano fale mais 120")
          .build();

      List<PlanoOutPutDto> faker = new ArrayList<>();
      faker.add(plano30);
      faker.add(plano60);
      faker.add(plano120);

      final var requisicao = Arrays.asList(RestAssured.given().headers(headers)
          .when()
          .get("api/plano/")
          .then()
          .statusCode(HttpStatus.OK.value())
          .extract().jsonPath()
          .getObject("", PlanoOutPutDto[].class));


      assertThat(requisicao).isEqualTo(faker);

    }

    @Test
    @DisplayName("testando a falha do metodo ")
    public void findAll_fail() {

    }


    @Nested
    @DisplayName("testando o end point busca")
    class TabelaValorPorMinutoOutPutDto {


      @Test
      @DisplayName("testando o sucesso do metodo ")
      public void success() throws Exception {

        final var requisicao = RestAssured.given().headers(headers)
            .param("dddOrigem", 11)
            .param("dddDestino", 16)
            .param("tempo", 40)
            .param("id_plan", 2L)
            .when()
            .get("api/plano/busca/")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract().jsonPath()
            .getObject("", ValorPorMinutoOutPutDto.class);

      }
    }

    @Test
    @DisplayName("testando falha com os param null")
    public void fail_params_null() {

      final var requisicao = RestAssured.given().headers(headers)
          .when()
          .get("api/plano/busca/")
          .then()
          .statusCode(HttpStatus.BAD_REQUEST.value());
    }
  }

  @Test
  @DisplayName("testando falha do id plano 1")
  public void fail_id_plan_1() {
    ValorPorMinutoDto dto = ValorPorMinutoDto.builder()
        .dddOrigem(11).dddDestino(16)
        .tempo(new BigDecimal(40))
        .idPlano(1L)
        .build();

    final var requisicao = RestAssured.given().headers(headers)
        .body(dto)
        .when()
        .get("api/plano/busca/")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  @DisplayName("testando falha de dddOrigem e dddDestino invalidos")
  public void fail_dddO_dddD() {
    ValorPorMinutoDto dto = ValorPorMinutoDto.builder()
        .tempo(new BigDecimal(40))
        .idPlano(2L)
        .build();

    final var requisicao = RestAssured.given().headers(headers)
        .param("dddOrigem", 12)
        .param("dddDestino", 23)
        .param("tempo", 40)
        .param("id_plan", 2L).when()
        .get("api/plano/busca/")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  @DisplayName("testando sucesso sem o plano")
  public void success_sem_plano() {
    ValorPorMinutoDto dto = ValorPorMinutoDto.builder()
        .dddOrigem(11).dddDestino(16).idPlano(2L)
        .tempo(BigDecimal.ZERO)
        .build();

    final var requisicao = RestAssured.given().headers(headers)
        .param("dddOrigem",11)
        .param("dddDestino",16)
        .param("tempo", 40)
        .param("id_plan",1L)
        .when()
        .get("api/plano/busca/")
        .then()
        .statusCode(HttpStatus.OK.value())
        .extract().jsonPath()
        .getObject("", ValorPorMinutoOutPutDto.class);

  }
}



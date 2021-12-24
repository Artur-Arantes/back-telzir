package br.com.artur.telzir.controller;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import br.com.artur.telzir.domain.Plano;
import br.com.artur.telzir.domain.TabelaValorPorMinuto;
import br.com.artur.telzir.domain.dto.PlanoOutPutDto;
import br.com.artur.telzir.domain.dto.ValorPorMinutoDto;
import br.com.artur.telzir.domain.dto.ValorPorMinutoOutPutDto;
import br.com.artur.telzir.exceptions.PlanoInvalidoException;
import br.com.artur.telzir.repositories.PlanoRepository;
import br.com.artur.telzir.repositories.ValorPorMinutoRepository;
import br.com.artur.telzir.service.imp.PlanoServiceImpl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceImplTest {

  @Mock
  PlanoRepository planoRepository;

  @Mock
  ValorPorMinutoRepository valorPorMinutoRepository;

  @InjectMocks
  PlanoServiceImpl planoService;

  @BeforeEach
  public void setUp() {

  }

  @Nested
  @DisplayName("Testando o metodo findAll")
  public class FindAll {

    @Test
    @DisplayName("testando o sucesso do metodo")
    public void findAll_success() {
      when(planoRepository.findAll()).thenReturn(Arrays.asList(Plano.builder().id(2L).build()));
      assertThat(planoService.findAll()).isEqualTo(Arrays.asList(PlanoOutPutDto.builder()
          .id(2L).build()));
    }

    @Test
    @DisplayName("testnado o metodo buscando pelo id 1(sem plano)")
    public void findAll_empty_list() {
      when(planoRepository.findAll()).thenReturn(Arrays.asList(Plano.builder().id(1L).build()));
      assertThat(planoService.findAll()).isEqualTo(Arrays.asList());
    }
  }

  @Nested
  @DisplayName("testando calculadora")
  class Calculadora {

    @Test
    @DisplayName("testando o sucesso do metodo com taxa do plano")
    public void calculadora_success() {
      final var plano = mock(Plano.class);
      final var valorMin = mock(TabelaValorPorMinuto.class);
      final var valor = ValorPorMinutoOutPutDto.builder()
          .taxaNormalDaLigacao(new BigDecimal("76.00"))
          .dddDestino(16)
          .dddOrigem(11)
          .tempo(new BigDecimal("40"))
          .comFaleMais(new BigDecimal("20.9000"))
          .build();
      when(planoRepository.findByNome(anyString())).thenReturn(Optional.of(plano));
      when(plano.getTempoDoPlano()).thenReturn(new BigDecimal("30"));
      when(planoRepository.findById(2L)).thenReturn(Optional.of(plano));
      when(valorPorMinutoRepository.findByDddOrigemAndDddDestino(any(), any()))
          .thenReturn(Optional.of(valorMin));
      when(valorMin.getTaxaNormalPorMinuto()).thenReturn(new BigDecimal("1.90"));
      assertThat(planoService.calculadora(11, 16, BigDecimal.valueOf(40), 2l)).isEqualTo(valor);
    }
  }

  @Test
  @DisplayName("testando com id 1(sem plano) como parametro")
  public void calculadora_fail() {
    final var plano = mock(Plano.class);
    final var dto = mock(ValorPorMinutoDto.class);
    when(planoRepository.findByNome(anyString())).thenReturn(Optional.of(plano));
    when(planoRepository.findById(dto.getIdPlano())).thenReturn(Optional.empty());
    assertThatExceptionOfType(PlanoInvalidoException.class).isThrownBy(() ->
        planoService.calculadora(11, 16, BigDecimal.valueOf(40), BigDecimal.ZERO.longValue()));

  }

  @Test
  @DisplayName("testando ")
  public void calculadora() {
    final var plano = mock(Plano.class);
    final var dto = mock(ValorPorMinutoDto.class);
    final var valorMin = mock(TabelaValorPorMinuto.class);
    final var valor = ValorPorMinutoOutPutDto.builder()
        .taxaNormalDaLigacao(new BigDecimal("57.00"))
        .dddDestino(16)
        .dddOrigem(11)
        .tempo(new BigDecimal("30"))
        .comFaleMais(BigDecimal.ZERO)
        .build();
    when(planoRepository.findByNome(anyString())).thenReturn(Optional.of(plano));
    when(valorMin.getTaxaNormalPorMinuto()).thenReturn(new BigDecimal("1.90"));
    when(plano.getTempoDoPlano()).thenReturn(new BigDecimal("30"));
    when(planoRepository.findById(2L)).thenReturn(Optional.of(plano));

    when(valorPorMinutoRepository.findByDddOrigemAndDddDestino(any(), any()))
        .thenReturn(Optional.of(valorMin));
    assertThat(planoService.calculadora(11, 16, BigDecimal.valueOf(30), 2L)).isEqualTo(valor);
  }
}

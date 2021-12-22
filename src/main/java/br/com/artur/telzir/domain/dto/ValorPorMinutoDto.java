package br.com.artur.telzir.domain.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class ValorPorMinutoDto {

  private BigDecimal tempo;

  private String dddOrigem;

  private String dddDdestino;

  private Long idPlano;

  private BigDecimal taxaNormalPorMinuto;
}

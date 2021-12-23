package br.com.artur.telzir.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValorPorMinutoDto {


  private BigDecimal tempo;

  private Integer dddOrigem;

  private Integer dddDestino;

  @JsonProperty("id_plan")
  private Long idPlano;

  private BigDecimal taxaNormalPorMinuto;
}

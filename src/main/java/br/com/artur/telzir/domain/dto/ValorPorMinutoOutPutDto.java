package br.com.artur.telzir.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@EqualsAndHashCode
public class ValorPorMinutoOutPutDto {

  @JsonProperty("tempo")
  private BigDecimal tempo;

  @JsonProperty("plano")
  private String plano;

  @JsonProperty("comFaleMais")
  private BigDecimal comFaleMais;

  @JsonProperty("taxaNormalDaLigacao")
  private BigDecimal taxaNormalDaLigacao;

  @JsonProperty("dddOrigem")
  private String dddOrigem;

  @JsonProperty("dddDestino")
  private String dddDestino;


}

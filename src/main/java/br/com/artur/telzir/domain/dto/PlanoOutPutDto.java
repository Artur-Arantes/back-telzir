package br.com.artur.telzir.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@EqualsAndHashCode(of = "id")
@Setter
@ToString
public class PlanoOutPutDto {


  @JsonProperty("nome")
  private String nome;

  @JsonProperty("id")
  private Long id;
}

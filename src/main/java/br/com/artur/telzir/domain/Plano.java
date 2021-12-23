package br.com.artur.telzir.domain;

import br.com.artur.telzir.domain.dto.PlanoOutPutDto;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "plano")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "id")
public class Plano {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_plan")
  private Long id;

  @Column(name = "nom_plan")
  private String nome;

  @Column(name = "temp_ise_plan")
  private BigDecimal tempoDoPlano;

  @Column(name = "tax_plan")
  private Float taxa;

  public PlanoOutPutDto planoToOutPut() {
    final var planoOutPut = PlanoOutPutDto.builder().id(id).nome(nome).build();

    return planoOutPut;
  }
}

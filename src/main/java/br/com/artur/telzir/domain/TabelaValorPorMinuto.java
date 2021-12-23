package br.com.artur.telzir.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "valor_por_minuto")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Generated
@EqualsAndHashCode(of = "id")
public class TabelaValorPorMinuto {

  @Id
  @GeneratedValue
  @Column(name = "id_val_min")
  private Long id;

  @Column(name = "ddd_ori_val_min")
  private Integer dddOrigem;

  @Column(name = "ddd_des_val_min")
  private Integer dddDestino;

  @Column(name = "val_min_val_min")
  private BigDecimal taxaNormalPorMinuto;

}

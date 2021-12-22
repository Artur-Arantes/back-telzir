package br.com.artur.telzir.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "valor_por_minuto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TabelaValorPorMinuto {

  @Id
  @GeneratedValue
  @Column(name = "id_tab")
  private Long id;

  @Column(name = "ddd_ori_tab")
  private String dddOrigem;

  @Column(name = "ddd_dest_tab")
  private String dddDestino;

  @Column(name = "val_min_val_min ")
  private BigDecimal taxaNormalPorMinuto;


}

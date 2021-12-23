package br.com.artur.telzir.repositories;

import br.com.artur.telzir.domain.TabelaValorPorMinuto;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ValorPorMinutoRepository extends CrudRepository<TabelaValorPorMinuto, Long> {

  Optional<TabelaValorPorMinuto> findByDddOrigemAndDddDestino(Integer dddOrigem,
                                                              Integer dddDestino);
}

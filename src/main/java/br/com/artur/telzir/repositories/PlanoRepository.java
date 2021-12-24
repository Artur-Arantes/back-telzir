package br.com.artur.telzir.repositories;

import br.com.artur.telzir.domain.Plano;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PlanoRepository extends CrudRepository<Plano, Long> {
  Optional<Plano>  findByNome(String nome);


}

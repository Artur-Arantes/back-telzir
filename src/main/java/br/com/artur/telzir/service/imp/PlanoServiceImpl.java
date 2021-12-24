package br.com.artur.telzir.service.imp;

import br.com.artur.telzir.domain.Plano;
import br.com.artur.telzir.domain.dto.PlanoOutPutDto;
import br.com.artur.telzir.domain.dto.ValorPorMinutoOutPutDto;
import br.com.artur.telzir.exceptions.DddOrigemOuDestinoInvalidos;
import br.com.artur.telzir.exceptions.PlanoInvalidoException;
import br.com.artur.telzir.repositories.PlanoRepository;
import br.com.artur.telzir.repositories.ValorPorMinutoRepository;
import br.com.artur.telzir.service.PlanoService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanoServiceImpl implements PlanoService {

  public static final BigDecimal TAXA_ADICIONAL = new BigDecimal("1.10");
  public static final Long SEM_PLANO = 1L;
  private final PlanoRepository planoRepository;
  private final ValorPorMinutoRepository valorPorMinutoRepository;

  @Transactional
  @Override
  public List<PlanoOutPutDto> findAll() {
    final List<Plano> planoBancoDeDados = (List) planoRepository.findAll();
    final var retorno =
        planoBancoDeDados.stream().map(t -> t.planoToOutPut())
            .filter(plano -> !plano.getId().equals(SEM_PLANO)).collect(Collectors.toList());
    return retorno;
  }

  @Override
  @Transactional
  public ValorPorMinutoOutPutDto calculadora(final Integer dddOrigem,
                                             final Integer dddDestino,
                                             final BigDecimal tempo, final Long plano) {
    log.info("Recebendo Plano: {0}", dddOrigem, dddDestino, tempo, plano);
    final var outPutDto = new ValorPorMinutoOutPutDto();
    final var semPlano =
        planoRepository.findByNome("Sem Plano").orElseThrow(NullPointerException::new);

    outPutDto.setTempo(tempo);
    outPutDto.setDddDestino(dddDestino);
    outPutDto.setDddOrigem(dddOrigem);

    final var planoSelecionado =
        planoRepository.findById(plano)
            .filter(p -> !plano.equals(semPlano.getId()))
            .orElseThrow(PlanoInvalidoException::new);

    outPutDto.setPlano(planoSelecionado.getNome());

    final var taxaNormalDaLigacao =
        valorPorMinutoRepository.findByDddOrigemAndDddDestino(dddOrigem,
            dddDestino).orElseThrow(DddOrigemOuDestinoInvalidos::new);

    final var resultadoSemPlano =
        taxaNormalDaLigacao.getTaxaNormalPorMinuto().multiply(tempo);

    outPutDto.setTaxaNormalDaLigacao(resultadoSemPlano);

    final var tempoDoPlano = planoSelecionado.getTempoDoPlano();

    final var tempoRestante = tempo.subtract(tempoDoPlano);

    if (tempoRestante.compareTo(BigDecimal.ZERO) > 0) {
      final var valorLigacaoMutiplcadoPelaTaxa =
          tempoRestante.multiply(TAXA_ADICIONAL);

      final var resultadoValorExtra = valorLigacaoMutiplcadoPelaTaxa
          .multiply(taxaNormalDaLigacao.getTaxaNormalPorMinuto());
      outPutDto.setComFaleMais(resultadoValorExtra);


    } else {
      outPutDto.setComFaleMais(BigDecimal.ZERO);
    }
    return outPutDto;
  }

}

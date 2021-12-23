package br.com.artur.telzir.service;

import br.com.artur.telzir.domain.dto.PlanoOutPutDto;
import br.com.artur.telzir.domain.dto.ValorPorMinutoOutPutDto;
import java.math.BigDecimal;
import java.util.List;

public interface PlanoService {

  List<PlanoOutPutDto> findAll();

  ValorPorMinutoOutPutDto calculadora(Integer dddOrigem, Integer dddDestino, BigDecimal tempo,
                                      Long plano);
}

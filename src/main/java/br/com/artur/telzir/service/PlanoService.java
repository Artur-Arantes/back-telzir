package br.com.artur.telzir.service;

import br.com.artur.telzir.domain.dto.PlanoOutPutDto;
import br.com.artur.telzir.domain.dto.ValorPorMinutoDto;
import br.com.artur.telzir.domain.dto.ValorPorMinutoOutPutDto;
import java.util.List;

public interface PlanoService {

  List<PlanoOutPutDto> findAll();

  ValorPorMinutoOutPutDto calculadora(ValorPorMinutoDto planoDto);
}

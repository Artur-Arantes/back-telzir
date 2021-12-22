package br.com.artur.telzir.controller;


import static org.springframework.web.bind.annotation.RequestMethod.GET;

import br.com.artur.telzir.domain.dto.PlanoOutPutDto;
import br.com.artur.telzir.domain.dto.ValorPorMinutoDto;
import br.com.artur.telzir.domain.dto.ValorPorMinutoOutPutDto;
import br.com.artur.telzir.service.PlanoService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/plano", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanoController {

  private final PlanoService service;

  @Transactional
  @RequestMapping(method = GET, value = "/")
  public List<PlanoOutPutDto> findAll() {
    return service.findAll();
  }

  @Transactional
  @RequestMapping(method = GET, value = "/busca")
  public ValorPorMinutoOutPutDto TabelaValoPorMinutoOutPutDto(ValorPorMinutoDto dto) {
    return service.calculadora(dto);
  }
}

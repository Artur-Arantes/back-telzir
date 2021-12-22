package br.com.artur.telzir.exceptions;

import static java.text.MessageFormat.format;

import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Getter
@Generated
public class PlanoInvalidoException extends RuntimeException {
  private final static String ERROR_MESSAGE = "Plano invalido ou nao encontrado";

  @SneakyThrows
  @Override
  public String getMessage() {
    return format(ERROR_MESSAGE);
  }
}
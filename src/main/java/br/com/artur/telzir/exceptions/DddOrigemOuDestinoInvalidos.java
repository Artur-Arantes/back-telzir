package br.com.artur.telzir.exceptions;

import static java.text.MessageFormat.format;

import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Getter
@Generated
public class DddOrigemOuDestinoInvalidos extends RuntimeException {

  private final static String ERROR_MESSAGE =
      "Ddd de Origem ou destino invalidos ou combinacao invalida";

  @SneakyThrows
  @Override
  public String getMessage() {
    return format(ERROR_MESSAGE);
  }
}

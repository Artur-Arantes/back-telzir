package br.com.artur.telzir.config;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import br.com.artur.telzir.exceptions.DddOrigemOuDestinoInvalidos;
import br.com.artur.telzir.exceptions.PlanoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdviceRest {
  @ExceptionHandler(PlanoInvalidoException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> resourceConflict(PlanoInvalidoException ex) {
    return new ResponseEntity<String>(ex.getMessage(), BAD_REQUEST);
  }

  @ExceptionHandler(DddOrigemOuDestinoInvalidos.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> resourceConflict(DddOrigemOuDestinoInvalidos ex) {
    return new ResponseEntity<String>(ex.getMessage(), BAD_REQUEST);
  }
}

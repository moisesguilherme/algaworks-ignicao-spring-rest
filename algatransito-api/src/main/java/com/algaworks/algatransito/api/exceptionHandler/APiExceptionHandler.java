package com.algaworks.algatransito.api.exceptionHandler;

import com.algaworks.algatransito.domain.exception.NegocioException;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class APiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        problemDetail.setType(URI.create("https://algatransito.com/erros/compos-invalidos"));

        Map<String, String> fields = ex.getBindingResult().getAllErrors()
               .stream()
               .collect(Collectors.toMap(objectError -> ((FieldError)objectError).getField(),
                       DefaultMessageSourceResolvable::getDefaultMessage)); //objectError -> objectError.getDefaultMessage()));

               //.forEach( objectError -> System.out.println(((FieldError) objectError).getField() + " - " + objectError.getDefaultMessage()));

        problemDetail.setProperty("fields", fields);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage()); //400
    }

}

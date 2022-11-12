package org.ddemianyk.words.wordrelationship.exceptionhandler;

import org.ddemianyk.words.wordrelationship.dto.WordPairExistsErrorDTO;
import org.ddemianyk.words.wordrelationship.exception.WordPairAlreadyExistsException;
import org.ddemianyk.words.wordrelationship.dto.FieldErrorResponseDTO;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public FieldErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldErrorResponseDTO.ErrorMessageDTO> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> new FieldErrorResponseDTO.ErrorMessageDTO(err.getField(), err.getDefaultMessage()))
                .distinct()
                .collect(toList());
        return new FieldErrorResponseDTO(errorMessages);
    }

    @ExceptionHandler(WordPairAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public WordPairExistsErrorDTO handleWordPairAlreadyExistsException(WordPairAlreadyExistsException exception) {
        return new WordPairExistsErrorDTO(exception.getMessage());
    }
}

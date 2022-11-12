package org.examddemianyk.words.wordrelationship.exceptionhandler;

import org.examddemianyk.words.wordrelationship.dto.ErrorResponseDTO;
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
    public ErrorResponseDTO handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<ErrorResponseDTO.ErrorMessageDTO> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorResponseDTO.ErrorMessageDTO(err.getField(), err.getDefaultMessage()))
                .distinct()
                .collect(toList());
        return new ErrorResponseDTO(errorMessages);
    }
}

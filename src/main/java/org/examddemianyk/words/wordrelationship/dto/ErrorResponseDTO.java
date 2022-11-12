package org.examddemianyk.words.wordrelationship.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ErrorResponseDTO {

    private final List<ErrorMessageDTO> errors;

    @Getter
    @RequiredArgsConstructor
    public static class ErrorMessageDTO {
        private final String field;
        private final String message;
    }
}

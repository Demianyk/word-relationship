package org.examddemianyk.words.wordrelationship.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WordPairExistsErrorDTO {
    private final String error;
}

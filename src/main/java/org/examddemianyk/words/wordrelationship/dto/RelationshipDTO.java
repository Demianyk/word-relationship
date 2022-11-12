package org.examddemianyk.words.wordrelationship.dto;

import lombok.Getter;
import lombok.Setter;
import org.examddemianyk.words.wordrelationship.model.RelType;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RelationshipDTO {

    @NotNull(message = "W1 can't be null")
    private String w1;

    @NotNull(message = "W2 can't be null")
    private String w2;

    @NotNull(message = "R can't be null")
    private RelType r;
}

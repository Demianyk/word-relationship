package org.ddemianyk.words.wordrelationship.dto;

import lombok.Getter;
import lombok.Setter;
import org.ddemianyk.words.wordrelationship.model.RelType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RelationshipDTO {

    @Pattern(regexp = "[a-zA-z\\s]*", message = "Only letters and spaces are allowed for W1 values")
    @NotNull(message = "W1 can't be null")
    private String w1;

    @Pattern(regexp = "[a-zA-z\\s]*", message = "Only letters and spaces are allowed for W2 values")
    @NotNull(message = "W2 can't be null")
    private String w2;

    @NotNull(message = "R can't be null")
    private RelType r;
}

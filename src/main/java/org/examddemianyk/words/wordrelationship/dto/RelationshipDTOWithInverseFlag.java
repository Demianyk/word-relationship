package org.examddemianyk.words.wordrelationship.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelationshipDTOWithInverseFlag extends RelationshipDTO {
    private boolean inverse;
}

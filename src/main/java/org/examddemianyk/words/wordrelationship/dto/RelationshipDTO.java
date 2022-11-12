package org.examddemianyk.words.wordrelationship.dto;

import lombok.Getter;
import lombok.Setter;
import org.examddemianyk.words.wordrelationship.model.RelType;

@Getter
@Setter
public class RelationshipDTO {

    private String W1;

    private String W2;

    private RelType R;
}

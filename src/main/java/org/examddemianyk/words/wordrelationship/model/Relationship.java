package org.examddemianyk.words.wordrelationship.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WORD_RELATIONSHIP")
@Getter
@Setter
public class Relationship {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "WORD_1")
    private String word1;

    @Column(name = "WORD_2")
    private String word2;

    @Column(name = "RELATIONSHIP_TYPE")
    private RelType relType;

}

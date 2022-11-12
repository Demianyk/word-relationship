package org.examddemianyk.words.wordrelationship.repository;

import org.examddemianyk.words.wordrelationship.model.RelType;
import org.examddemianyk.words.wordrelationship.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, String> {
    List<Relationship> findAllByRelType(RelType relType);
}

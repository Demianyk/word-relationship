package org.examddemianyk.words.wordrelationship.repository;

import org.examddemianyk.words.wordrelationship.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, String> {
}

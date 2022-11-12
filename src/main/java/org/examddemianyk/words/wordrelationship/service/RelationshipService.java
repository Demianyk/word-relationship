package org.examddemianyk.words.wordrelationship.service;

import lombok.RequiredArgsConstructor;
import org.examddemianyk.words.wordrelationship.dto.RelationshipDTO;
import org.examddemianyk.words.wordrelationship.exception.WordPairAlreadyExistsException;
import org.examddemianyk.words.wordrelationship.model.RelType;
import org.examddemianyk.words.wordrelationship.model.Relationship;
import org.examddemianyk.words.wordrelationship.repository.RelationshipRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class RelationshipService {
    private final RelationshipRepository relationshipRepository;

    @Transactional
    public void save(RelationshipDTO relationshipDTO) {
        normalizeDTO(relationshipDTO);
        Relationship relationship = toModel(relationshipDTO);
        validateWordPairUniqueness(relationship);
        relationshipRepository.save(relationship);
    }

    @Transactional
    public List<RelationshipDTO> listAll() {
        return relationshipRepository.findAll().stream().map(this::toDTO).collect(toList());
    }

    @Transactional
    public List<RelationshipDTO> listForRelType(RelType relType) {
        return relationshipRepository.findAllByRelType(relType).stream().map(this::toDTO).collect(toList());
    }

    private Relationship toModel(RelationshipDTO dto) {
        Relationship relationship = new Relationship();
        relationship.setId(generateId(dto));
        relationship.setWord1(normalizeWord(dto.getW1()));
        relationship.setWord2(normalizeWord(dto.getW2()));
        relationship.setRelType(dto.getR());
        return relationship;
    }

    private RelationshipDTO toDTO(Relationship relationship) {
        RelationshipDTO dto = new RelationshipDTO();
        dto.setW1(relationship.getWord1());
        dto.setW2(relationship.getWord2());
        dto.setR(relationship.getRelType());
        return dto;
    }

    private static void normalizeDTO(RelationshipDTO dto) {
        dto.setW1(normalizeWord(dto.getW1()));
        dto.setW2(normalizeWord(dto.getW2()));
    }

    private static String normalizeWord(String word) {
     return word.trim().toLowerCase();
    }

    private static String generateId(RelationshipDTO dto) {
        return Stream.of(dto.getW1(), dto.getW2()).sorted().collect(Collectors.joining("+"));
    }

    private void validateWordPairUniqueness(Relationship relationship) {
        if (relationshipRepository.findById(relationship.getId()).isPresent()) {
            throw new WordPairAlreadyExistsException(relationship.getWord1(), relationship.getWord2());
        }
    }
}

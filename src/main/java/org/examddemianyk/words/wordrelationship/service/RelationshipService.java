package org.examddemianyk.words.wordrelationship.service;

import lombok.RequiredArgsConstructor;
import org.examddemianyk.words.wordrelationship.dto.RelationshipDTO;
import org.examddemianyk.words.wordrelationship.dto.RelationshipDTOWithInverseFlag;
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
    public List<RelationshipDTO> listAll(boolean inverseRequired) {
        return listAsDTOs(relationshipRepository.findAll(), inverseRequired);
    }

    @Transactional
    public List<RelationshipDTO> listForRelType(RelType relType, boolean inverseRequired) {
        return listAsDTOs(relationshipRepository.findAllByRelType(relType), inverseRequired);
    }

    private List<RelationshipDTO> listAsDTOs(List<Relationship> relationships, boolean inverseRequired) {
        if (inverseRequired) {
            return withInverses(relationships);
        }
        return withoutInverse(relationships);
    }

    private Relationship toModel(RelationshipDTO dto) {
        Relationship relationship = new Relationship();
        relationship.setId(generateId(dto));
        relationship.setWord1(normalizeWord(dto.getW1()));
        relationship.setWord2(normalizeWord(dto.getW2()));
        relationship.setRelType(dto.getR());
        return relationship;
    }

    private static RelationshipDTO toDTO(Relationship relationship) {
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

    private static RelationshipDTO toDtoWithInverse(RelationshipDTO dto, boolean inverse) {
        RelationshipDTOWithInverseFlag dtoWithInverse = new RelationshipDTOWithInverseFlag();
        dtoWithInverse.setInverse(inverse);
        if (inverse) {
            dtoWithInverse.setW1(dto.getW2());
            dtoWithInverse.setW2(dto.getW1());
        } else {
            dtoWithInverse.setW1(dto.getW1());
            dtoWithInverse.setW2(dto.getW2());
        }
        dtoWithInverse.setR(dto.getR());
        return dtoWithInverse;
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

    private static List<RelationshipDTO> withInverses(List<Relationship> relationships) {
        List<RelationshipDTO> result = relationships.stream()
                .map(RelationshipService::toDTO)
                .map(dto -> toDtoWithInverse(dto, false))
                .collect(toList());
        result.addAll(relationships.stream()
                .map(RelationshipService::toDTO)
                .map(dto -> toDtoWithInverse(dto, true))
                .collect(toList()));
        return result;
    }

    private static List<RelationshipDTO> withoutInverse(List<Relationship> relationships) {
        return relationships.stream()
                .map(RelationshipService::toDTO)
                .collect(toList());
    }
}

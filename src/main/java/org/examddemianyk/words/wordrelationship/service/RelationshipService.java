package org.examddemianyk.words.wordrelationship.service;

import lombok.RequiredArgsConstructor;
import org.examddemianyk.words.wordrelationship.dto.RelationshipDTO;
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
        relationshipRepository.save(toModel(relationshipDTO));
    }

    @Transactional
    public List<RelationshipDTO> listAll() {
        return relationshipRepository.findAll().stream().map(this::toDTO).collect(toList());
    }

    private Relationship toModel(RelationshipDTO dto) {
        Relationship relationship = new Relationship();
        String id = Stream.of(dto.getW1(), dto.getW2()).sorted().collect(Collectors.joining("+"));
        relationship.setId(id);
        relationship.setWord1(dto.getW1());
        relationship.setWord2(dto.getW2());
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
}

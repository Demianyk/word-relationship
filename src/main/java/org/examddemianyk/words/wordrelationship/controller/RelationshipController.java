package org.examddemianyk.words.wordrelationship.controller;

import lombok.RequiredArgsConstructor;
import org.examddemianyk.words.wordrelationship.dto.RelationshipDTO;
import org.examddemianyk.words.wordrelationship.service.RelationshipService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/relationship")
@RequiredArgsConstructor
@Validated
public class RelationshipController {

    private final RelationshipService relationshipService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody RelationshipDTO relationshipDTO) {
        relationshipService.save(relationshipDTO);
    }

    @GetMapping
    public List<RelationshipDTO> listAll() {
        return relationshipService.listAll();
    }
}

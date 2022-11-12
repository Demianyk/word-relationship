package org.examddemianyk.words.wordrelationship.controller;

import lombok.RequiredArgsConstructor;
import org.examddemianyk.words.wordrelationship.dto.RelationshipDTO;
import org.examddemianyk.words.wordrelationship.model.RelType;
import org.examddemianyk.words.wordrelationship.service.RelationshipService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
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

    @GetMapping()
    public List<RelationshipDTO> listAll(
            @RequestParam(value = "inverse_required", required = false, defaultValue = "false") boolean inverseRequired) {
        return relationshipService.listAll(inverseRequired);
    }

    @GetMapping("/{relType}")
    public List<RelationshipDTO> listForRelationshipType(
            @PathVariable("relType") RelType relType,
            @RequestParam(value = "inverse_required", required = false, defaultValue = "false") boolean inverseRequired) {
        return relationshipService.listForRelType(relType, inverseRequired);
    }
}

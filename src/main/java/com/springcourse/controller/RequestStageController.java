package com.springcourse.controller;

import com.springcourse.domain.RequestStage;
import com.springcourse.services.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "request-stages")
public class RequestStageController {

    private RequestStageService requestStageService;

    @Autowired
    public RequestStageController(RequestStageService requestStageService) {
        this.requestStageService = requestStageService;
    }

    @PostMapping
    public ResponseEntity<RequestStage> save(@RequestBody RequestStage requestStage) {
        RequestStage createdRequestStage = requestStageService.save(requestStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestStage> getRequestStageById(@PathVariable(name = "id") Long id) {
        RequestStage getRequestStageById = requestStageService.getById(id);
        return ResponseEntity.ok(getRequestStageById);
    }
}

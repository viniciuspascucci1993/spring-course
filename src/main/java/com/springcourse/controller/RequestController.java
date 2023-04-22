package com.springcourse.controller;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.services.RequestService;
import com.springcourse.services.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "requests")
public class RequestController {

    private RequestService requestService;
    private RequestStageService requestStageService;

    @Autowired
    public RequestController(RequestService requestService, RequestStageService requestStageService) {
        this.requestService = requestService;
        this.requestStageService = requestStageService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable(name = "id") Long id) {
        Request request = requestService.getById(id);
        return ResponseEntity.ok(request);
    }

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody Request request) {
        Request createdRequest = requestService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody Request request) {
        request.setId(id);
        Request updatedRequest = requestService.updateRequest(request);
        return ResponseEntity.ok(updatedRequest);
    }

    @GetMapping
    public ResponseEntity<List<Request>> getRequestListAll() {
        List<Request> listRequests = requestService.getList();
        return ResponseEntity.ok(listRequests);
    }

    @GetMapping("/{id}/request-stages")
    public ResponseEntity<List<RequestStage>> getListOffAllRequestStages(@PathVariable(name = "id") Long id) {
        List<RequestStage> stages = requestStageService.listAllByRequestId(id);
        return ResponseEntity.ok(stages);
    }
}

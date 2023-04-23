package com.springcourse.controller;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.model.PaginationModel;
import com.springcourse.model.PaginationRequestModel;
import com.springcourse.services.RequestService;
import com.springcourse.services.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<PaginationModel<Request>> getRequestsListPagination(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        PaginationRequestModel paginationRequestModel = new PaginationRequestModel(page, size);
        PaginationModel<Request> paginationModel = requestService.getListWithPagination(paginationRequestModel);

        return ResponseEntity.ok(paginationModel);
    }

    @GetMapping("/{id}/request-stages")
    public ResponseEntity<PaginationModel<RequestStage>> getListOffAllRequestStages(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {


        PaginationRequestModel paginationRequestModel = new PaginationRequestModel(page, size);
        PaginationModel<RequestStage> paginationModel = requestStageService
                .listAllByRequestStageIdOnLazyModel(id, paginationRequestModel);

        return ResponseEntity.ok(paginationModel);
    }
}

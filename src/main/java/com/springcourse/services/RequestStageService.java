package com.springcourse.services;

import com.springcourse.domain.RequestStage;
import com.springcourse.enums.RequestState;
import com.springcourse.exceptions.NotFoundException;
import com.springcourse.repositories.RequestRepository;
import com.springcourse.repositories.RequestStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestStageService {

    @Autowired
    private RequestStageRepository requestStageRepository;

    @Autowired
    private RequestRepository requestRepository;

    public RequestStage save(RequestStage stage) {
        stage.setRealizationDate(new Date());
        RequestStage requestStage = requestStageRepository.save(stage);

        Long requestId = requestStage.getRequest().getId();
        RequestState state = requestStage.getState();
        requestRepository.updateStatus(requestId, state);

        return requestStage;
    }

    public RequestStage getById(Long id) {
        Optional<RequestStage> requestStageRepositoryById = requestStageRepository.findById(id);
        return requestStageRepositoryById.orElseThrow(() -> new NotFoundException("Resource not found " + id));
    }

    public List<RequestStage> listAllByRequestId(Long id) {
        List<RequestStage> allByRequestId = requestStageRepository.findAllByRequestId(id);
        return allByRequestId;
    }
}

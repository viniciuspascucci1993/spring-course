package com.springcourse.services;

import com.springcourse.domain.Request;
import com.springcourse.enums.RequestState;
import com.springcourse.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request save( Request request ) {
        request = Request.builder()
                .state(RequestState.OPEN)
                .creationDate(new Date())
                .build();
        Request createdRequest = requestRepository.save(request);
        return createdRequest;
    }

    public Request updateRequest( Request request ) {
        Request updatedRequest = requestRepository.save(request);
        return updatedRequest;
    }

    public Request getById( Long id ) {
        Optional<Request> byId = requestRepository.findById(id);
        return byId.get();
    }

    public List<Request> getList() {
        List<Request> all = requestRepository.findAll();
        return all;
    }

    public List<Request> listAllByOwnerId(Long ownerId) {
        List<Request> allByOwnerId = requestRepository.findAllByOwnerId(ownerId);
        return allByOwnerId;
    }
}

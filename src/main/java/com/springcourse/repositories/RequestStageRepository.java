package com.springcourse.repositories;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.enums.RequestState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {

    List<RequestStage> findAllByRequestId(Long id);
}
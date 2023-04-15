package com.springcourse.repositories;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.enums.RequestState;
import com.springcourse.enums.Role;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestStageRepositoryTest {

    @Autowired
    private RequestStageRepository requestStageRepository;

    @Test
    public void AshouldSaveRequestStageTest() {
        RequestStage requestStage = RequestStage.builder()
                .id(null)
                .description("Foi comprado um Playstation 5 New e despachado para transportadora")
                .realizationDate(new Date())
                .state(RequestState.CLOSED)
                .request(Request.builder()
                        .id(1L)
                        .subject("Subject 01")
                        .description("Playstation 5 856GB")
                        .creationDate(null)
                        .code(187)
                        .state(RequestState.OPEN)
                        .build())
                .owner(User.builder()
                        .id(1L)
                        .name("Vinicius Pascucci")
                        .email("vinicius.pascucci1@gmail.com")
                        .registration(25447)
                        .code(2)
                        .password("xepa123")
                        .role(Role.ADMINISTRATOR)
                        .build())
                .build();

        RequestStage savedRequestStage = requestStageRepository.save(requestStage);
        assertThat(savedRequestStage.getId()).isEqualTo(1L);

    }

    @Test
    public void shouldGetRequestStageByIdTest() {

        Optional<RequestStage> requestStageRepositoryById = requestStageRepository.findById(1L);
        RequestStage requestStage = requestStageRepositoryById.get();

        assertThat(requestStage.getDescription()).isEqualTo(
                "Foi comprado um Playstation 5 New e despachado para transportadora");

    }

    @Test
    public void shouldListRequestStageTest() {

        List<RequestStage> listOfRequestStage = requestStageRepository.findAllByRequestId(1L);
        assertThat(listOfRequestStage.size()).isEqualTo(1);

    }

}

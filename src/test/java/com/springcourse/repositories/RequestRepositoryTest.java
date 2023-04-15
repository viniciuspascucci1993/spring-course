package com.springcourse.repositories;

import com.springcourse.domain.Request;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTest {

    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void AshouldSaveRequestTest() {

        Request request = Request.builder()
                .id(null)
                .subject("Subject 01")
                .description("Playstation 5")
                .creationDate(new Date())
                .code(187)
                .state(RequestState.OPEN)
                .owner(User.builder()
                        .id(1L)
                        .name("Vinicius Pascucci")
                        .email("vinicius.pascucci1@gmail.com")
                        .registration(25447)
                        .code(2)
                        .password("xepa123")
                        .role(Role.ADMINISTRATOR)
                        .build())
                .stages(null)
                .build();

        Request requestSaved = requestRepository.save(request);

        assertThat(requestSaved.getId()).isEqualTo(1L);
    }

    @Test
    public void shouldUpdateRequestTest() {

        Request request = Request.builder()
                .id(1L)
                .subject("Subject 01")
                .description("Playstation 5 856GB")
                .creationDate(null)
                .code(187)
                .state(RequestState.OPEN)
                .owner(User.builder()
                        .id(1L)
                        .name("Vinicius Pascucci")
                        .email("vinicius.pascucci1@gmail.com")
                        .registration(25447)
                        .code(2)
                        .password("xepa123")
                        .role(Role.ADMINISTRATOR)
                        .build())
                .stages(null)
                .build();

        Request requestUpdated = requestRepository.save(request);

        assertThat(requestUpdated.getDescription()).isEqualTo("Playstation 5 856GB");
    }

    @Test
    public void shouldGetRequestByIdTest()  {

        Optional<Request> retorno = requestRepository.findById(1L);
        Request request = retorno.get();

        assertThat(request.getSubject()).isEqualTo("Subject 01");
    }

    @Test
    public void shouldListRequestTest() {

        List<Request> listOfRequest = requestRepository.findAll();
        assertThat(listOfRequest.size()).isEqualTo(1);

    }

    @Test
    public void shouldListAllRequestTest() {

        List<Request> listOfRequest = requestRepository.findAllByOwnerId(1L);
        assertThat(listOfRequest.size()).isEqualTo(1);

    }

    @Test
    public void shouldUpdateStatusRequestTest() {

        int affectedRoles = requestRepository.updateStatus(1L, RequestState.IN_PROGRESS);
        assertThat(affectedRoles).isEqualTo(1);
    }

}

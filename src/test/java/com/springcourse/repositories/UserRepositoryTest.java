package com.springcourse.repositories;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.enums.RequestState;
import com.springcourse.enums.Role;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void AshouldSaveUser() {
        User u = User.builder()
                .id(null)
                .name("Vinicius Pascucci")
                .email("vinicius.pascucci1@gmail.com")
                .registration(25447)
                .code(2)
                .password("xepa123")
                .role(Role.ADMINISTRATOR)
                .requests(List.of(Request.builder()
                        .id(222L)
                        .subject("Item 01")
                        .description("Playstation 5")
                        .creationDate(new Date())
                        .code(12323)
                        .stages(new ArrayList<>())
                        .owner(User.builder()
                                .id(null)
                                .name("Vinicius Pascucci")
                                .email("vinicius.pascucci1@gmail.com")
                                .registration(25447)
                                .code(2)
                                .password("xepa123")
                                .role(Role.ADMINISTRATOR)
                                .build())
                                .stages(List.of(RequestStage.builder()
                                                .id(20L)
                                                .description("TESTE")
                                                .realizationDate(new Date())
                                                .state(RequestState.OPEN)
                                                .request(Request.builder()
                                                        .id(222L)
                                                        .subject("Item 01")
                                                        .description("Playstation 5")
                                                        .creationDate(new Date())
                                                        .code(12323)
                                                        .stages(new ArrayList<>())
                                                        .build())
                                                .owner(User.builder()
                                                        .id(null)
                                                        .name("Vinicius Pascucci")
                                                        .email("vinicius.pascucci1@gmail.com")
                                                        .registration(25447)
                                                        .code(2)
                                                        .password("xepa123")
                                                        .role(Role.ADMINISTRATOR)
                                                        .build())
                                        .build()))
                        .build()))
                .build();

        User createUser = userRepository.save(u);

        assertThat(createUser.getId()).isEqualTo(1L);
    }

    @Test
    public void shouldUpdate() {
        User u = User.builder()
                .id(1L)
                .name("Vinicius Torres Pascucci")
                .email("vinicius.pascucci1@gmail.com")
                .registration(25447)
                .code(2)
                .password("xepa123")
                .role(Role.ADMINISTRATOR)
                .requests(List.of(Request.builder()
                        .id(null)
                        .subject("Item 01")
                        .description("Playstation 5")
                        .creationDate(new Date())
                        .code(12323)
                        .stages(new ArrayList<>())
                        .owner(User.builder()
                                .id(1L)
                                .name("Vinicius Torres Pascucci")
                                .email("vinicius.pascucci1@gmail.com")
                                .registration(25447)
                                .code(2)
                                .password("xepa123")
                                .role(Role.ADMINISTRATOR)
                                .build())
                        .stages(List.of(RequestStage.builder()
                                .id(20L)
                                .description("TESTE")
                                .realizationDate(new Date())
                                .state(RequestState.OPEN)
                                .request(Request.builder()
                                        .id(null)
                                        .subject("Item 01")
                                        .description("Playstation 5")
                                        .creationDate(new Date())
                                        .code(12323)
                                        .stages(new ArrayList<>())
                                        .build())
                                .owner(User.builder()
                                        .id(1L)
                                        .name("Vinicius Torres Pascucci")
                                        .email("vinicius.pascucci1@gmail.com")
                                        .registration(25447)
                                        .code(2)
                                        .password("xepa123")
                                        .role(Role.ADMINISTRATOR)
                                        .build())
                                .build()))
                        .build()))
                .build();

        User updateUser = userRepository.save(u);

        assertThat(updateUser.getName()).isEqualTo("Vinicius Torres Pascucci");
    }

    @Test
    public void shouldGetUserById() {

        Optional<User> userById = userRepository.findById(1L);
        User user = userById.get();

        assertThat(user.getPassword()).isEqualTo("xepa123");
    }

    @Test
    public void listUserTest() {

        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(1);

    }

    @Test
    public void login() {

        Optional<User> retorno = userRepository.login("vinicius.pascucci1@gmail.com", "xepa123");
        User loggedUser = retorno.get();

        assertThat(loggedUser.getId()).isEqualTo(1L);

    }

}

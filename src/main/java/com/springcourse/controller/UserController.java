package com.springcourse.controller;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLoginDTO;
import com.springcourse.model.PaginationModel;
import com.springcourse.model.PaginationRequestModel;
import com.springcourse.services.RequestService;
import com.springcourse.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private UserService userService;
    private RequestService requestService;

    @Autowired
    public UserController(UserService userService, RequestService requestService) {
        this.userService = userService;
        this.requestService = requestService;

    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDTO userLoginDTO) {
        User userLogged = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return ResponseEntity.ok(userLogged);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User createdUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<PaginationModel<User>> getUsersList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        PaginationRequestModel paginationRequestModel = new PaginationRequestModel(page, size);
        PaginationModel<User> paginationModel = userService.getListWithPagination(paginationRequestModel);

        return ResponseEntity.ok(paginationModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(name = "id") Long id , @RequestBody User user) {
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);

    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PaginationModel<Request>> getLisstAllRequestsById(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        PaginationRequestModel paginationRequestModel = new PaginationRequestModel(page, size);
        PaginationModel<Request> paginationModel = requestService.listAllByOwnerIdOnLazyModel(id,
                paginationRequestModel);
        return ResponseEntity.ok(paginationModel);
    }


}

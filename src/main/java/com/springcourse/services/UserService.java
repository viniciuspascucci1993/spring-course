package com.springcourse.services;

import com.springcourse.domain.User;
import com.springcourse.exceptions.NotFoundException;
import com.springcourse.model.PaginationModel;
import com.springcourse.model.PaginationRequestModel;
import com.springcourse.repositories.UserRepository;
import com.springcourse.services.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save( User user ) {
        String hash = HashUtil.getSecurityHashForPassword(user.getPassword());
        user.setPassword(hash);
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    public User updateUser( User user ) {
        String hash = HashUtil.getSecurityHashForPassword(user.getPassword());
        user.setPassword(hash);
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    public User getById( Long id ) {
        Optional<User> getById = userRepository.findById(id);

        return getById.orElseThrow(() -> new NotFoundException("Resource not found " + id));
    }

    public List<User> getList() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public PaginationModel<User> getListWithPagination(PaginationRequestModel paginationRequestModel) {
        Pageable pageable = PageRequest.of(paginationRequestModel.getPage(), paginationRequestModel.getSize());
        Page<User> page = userRepository.findAll(pageable);

        PaginationModel<User> paginationModel = new PaginationModel<>((int)page.getTotalElements(),
                page.getSize(), page.getTotalPages(), page.getContent());

        return paginationModel;

    }

    public User login(String email, String password) {
        password = HashUtil.getSecurityHashForPassword(password);
        Optional<User> login = userRepository.login(email, password);
        return login.get();
    }
}

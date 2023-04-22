package com.springcourse.services;

import com.springcourse.domain.User;
import com.springcourse.exceptions.NotFoundException;
import com.springcourse.repositories.UserRepository;
import com.springcourse.services.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User login(String email, String password) {
        password = HashUtil.getSecurityHashForPassword(password);
        Optional<User> login = userRepository.login(email, password);
        return login.get();
    }
}

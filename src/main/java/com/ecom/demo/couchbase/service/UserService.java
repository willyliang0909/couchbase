package com.ecom.demo.couchbase.service;

import com.ecom.demo.couchbase.module.User;
import com.ecom.demo.couchbase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Page<User> getAllUser(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public User createUser(User user) {
        return repository.save(user);
    }
}

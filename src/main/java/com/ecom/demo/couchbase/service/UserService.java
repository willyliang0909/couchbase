package com.ecom.demo.couchbase.service;

import com.ecom.demo.couchbase.model.User;
import com.ecom.demo.couchbase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Page<User> getAllUser(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public User createUser(User user) {
        repository.save(user);
        User u = new User();
        //u.setId("replica-123");
        repository.save(u);
        return user;
    }
}

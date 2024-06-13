package com.ecom.demo.couchbase.controller;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryOptions;
import com.ecom.demo.couchbase.model.User;
import com.ecom.demo.couchbase.model.dto.UserTokenDto;
import com.ecom.demo.couchbase.repository.FlightPathRepository;
import com.ecom.demo.couchbase.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.ReactiveCouchbaseTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sql")
public class SqlController {

    private final Bucket bucket;

    private final CouchbaseTemplate couchbaseTemplate;

    private final FlightPathRepository repository;

    private final TokenRepository tokenRepository;

    @GetMapping("/{memberId}")
    public List<UserTokenDto> sql(@PathVariable Integer memberId) {
//        var result = bucket.scope("mod").query("select META(t).id , u.*, t.* from user u join token t on t.udid = u.udid");
//        for (var row : result.rowsAs(UserTokenDto.class)) {
//            System.out.println("Found row: " + row);
//        }
        //couchbaseTemplate.findById(User.class);
//        tokenRepository.getOperations()
        var a = tokenRepository.select(memberId);
        return a;
    }
}

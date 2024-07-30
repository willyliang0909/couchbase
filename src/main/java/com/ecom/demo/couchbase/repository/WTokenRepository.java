package com.ecom.demo.couchbase.repository;

import com.ecom.demo.couchbase.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WTokenRepository {

    private final CouchbaseTemplate couchbaseTemplate;

    public List<Token> findAll() {
        return couchbaseTemplate.findByQuery(Token.class).all();
    }
}

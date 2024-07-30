package com.ecom.demo.couchbase.repository;

import com.ecom.demo.couchbase.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.QueryCriteria;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WTokenRepository {

    private final CouchbaseTemplate couchbaseTemplate;

    public Token findById(String id) {
        return couchbaseTemplate.findById(Token.class).one(id);
    }

    public Collection<? extends Token> findByIds(Collection<String> ids) {
        return couchbaseTemplate.findById(Token.class).all(ids);
    }

    public List<Token> findByIdsInAndFetToken(Collection<String> ids, String fetToken) {
        QueryCriteria criteria = QueryCriteria
                .where("fetToken").is(fetToken)
                .and("META().`id`").in(ids.toArray());
        return couchbaseTemplate.findByQuery(Token.class).matching(new Query(criteria)).all();
    }

    public List<Token> findAll() {
        return couchbaseTemplate.findByQuery(Token.class).all();
    }

    public Token upsert(Token token) {
        return couchbaseTemplate.upsertById(Token.class).one(token);
    }
}

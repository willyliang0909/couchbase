package com.ecom.demo.couchbase.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.ecom.demo.couchbase.model.Token;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.DynamicProxyable;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Scope("seq3")
@Collection("token")
@Repository
@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
public interface TokenScopeRepository extends CouchbaseRepository<Token, String>, DynamicProxyable<TokenScopeRepository> {

    //@Scope("seq2")
    List<Token> findAllByFetToken(String fetToken);

}

package com.ecom.demo.couchbase.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.ecom.demo.couchbase.model.User;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.data.couchbase.repository.Scope;
import org.springframework.stereotype.Repository;

@Scope("mod")
@Collection("user")
@Repository
@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
public interface UserRepository extends CouchbaseRepository<User, String> {
}

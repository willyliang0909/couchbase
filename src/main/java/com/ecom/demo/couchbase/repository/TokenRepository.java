package com.ecom.demo.couchbase.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.ecom.demo.couchbase.model.Token;
import com.ecom.demo.couchbase.model.dto.UserTokenDto;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.data.couchbase.repository.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Scope("mod")
@Collection("token")
@Repository
@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
public interface TokenRepository extends CouchbaseRepository<Token, String> {

    List<Token> findAllByFetToken(String fetToken);

    List<Token> findAllByMemberIdAndUdidOrderByExpireDateTimeDesc(int memberId, String udid);

    List<Token> findByTokenInAndFetToken(List<String> tokens, String fetToken);

    @Query(value = "select meta(t).id as __id, " +
            " t.`scope`, t.memberId, t.fetToken," +
            " u.name, u.email, u.deviceId " +
            " from user u " +
            " join token t on t.udid = u.udid" +
            " where t.memberId = $memberId")
    List<UserTokenDto> select(Integer memberId);

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    List<Token> n1findAll();

}

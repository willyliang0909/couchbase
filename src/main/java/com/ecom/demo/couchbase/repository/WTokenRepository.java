package com.ecom.demo.couchbase.repository;

import com.couchbase.client.java.Scope;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryOptions;
import com.ecom.demo.couchbase.model.Token;
import com.ecom.demo.couchbase.model.dto.UserTokenDto;
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

    private final Scope scope;

    public List<UserTokenDto> sql(Integer memberId, Boolean isLast, Boolean isDelete) {

        String sql = "select t.`scope`, t.memberId, t.fetToken," +
                " u.name, u.email, u.deviceId " +
                " from user u " +
                " left join token t on t.udid = u.udid" +
                " where t.memberId = $memberId" +
                " and ($isLast is null or t.isLast = $isLast)" +
                " and ($isDelete is null or t.isDelete = $isDelete)";
                //(isLast != null ? " and t.is_last = $isLast" : "") +
                //(isDelete != null ? " and t.is_delete = $isDelete" : "");

        JsonObject params = JsonObject.create();
        params.put("memberId", memberId);
        params.put("isLast", isLast);
        params.put("isDelete", isDelete);

        var options = QueryOptions.queryOptions().parameters(params);

        return scope.query(sql, options).rowsAs(UserTokenDto.class);

//        return couchbaseTemplate.getCouchbaseClientFactory().getScope()
//                .query(sql.toString(), options)
//                .rowsAs(UserTokenDto.class);
    }

    public Token findById(String id) {
        return couchbaseTemplate.findById(Token.class).one(id);
    }

    public Token findByIdInScope(String scope, String id) {
        return couchbaseTemplate.findById(Token.class).inScope(scope).one(id);
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

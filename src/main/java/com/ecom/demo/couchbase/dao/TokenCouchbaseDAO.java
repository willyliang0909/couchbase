package com.ecom.demo.couchbase.dao;

import com.ecom.demo.couchbase.aop.TokenUpdate;
import com.ecom.demo.couchbase.model.Token;
import com.ecom.demo.couchbase.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenCouchbaseDAO extends TokenDAOImpl {

    private final TokenRepository tokenRepository;

    @TokenUpdate
    @Override
    public Token insertToken(Token token) {
//        boolean isUpdateCache = cacheUtil.isUpdateCache(CacheUtil.tokenCacheFlag);
        boolean isUpdateCache = true;
        if (isUpdateCache) {
            return tokenRepository.save(token);
        } else {
            return super.insertToken(token);
        }
    }

    @TokenUpdate
    @Override
    public void updateToken(Token token) {
        tokenRepository.save(token);
    }

    @Override
    public Token selectTokenByToken(String token) {
        return tokenRepository.findById(token).orElse(null);
    }

    @Override
    public Token selectTokenByFetToken(String fetToken) {
        return tokenRepository.findAllByFetToken(fetToken).stream().findFirst().orElse(null);
    }

    @Override
    public Token selectTokenByMemberIdAndDeviceId(int memberId, String udid) {
        return tokenRepository.findAllByMemberIdAndUdidOrderByExpireDateTimeDesc(memberId, udid).stream().findFirst().orElse(null);
    }
}

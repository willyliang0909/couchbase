package com.ecom.demo.couchbase.dao;

import com.ecom.demo.couchbase.model.Token;

public interface ITokenDAO {

    void insertToken(Token token);
    void updateToken(Token token);
    Token selectTokenByToken(String token);
    Token selectTokenByFetToken(String fetToken);
    Token selectTokenByMemberIdAndDeviceId(int memberId, String deviceId);

}

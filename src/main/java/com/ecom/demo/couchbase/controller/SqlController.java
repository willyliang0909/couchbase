package com.ecom.demo.couchbase.controller;

import com.ecom.demo.couchbase.model.dto.UserTokenDto;
import com.ecom.demo.couchbase.repository.TokenRepository;
import com.ecom.demo.couchbase.repository.WTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sql")
public class SqlController {

    private final TokenRepository tokenRepository;

    private final WTokenRepository wTokenRepository;

    @GetMapping("/repo/{memberId}")
    public List<UserTokenDto> sql(@PathVariable Integer memberId) {
        return tokenRepository.select(memberId, null, null);
    }

    @GetMapping("/template/{memberId}")
    public List<UserTokenDto> tempSql(@RequestParam Integer memberId) {
        return wTokenRepository.sql(memberId, null, null);
    }
}

package com.ecom.demo.couchbase.controller;

import com.ecom.demo.couchbase.dao.ITokenDAO;
import com.ecom.demo.couchbase.model.Token;
import com.ecom.demo.couchbase.model.dto.UserTokenDto;
import com.ecom.demo.couchbase.repository.TokenRepository;
import com.ecom.demo.couchbase.repository.WTokenRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tokens")
public class TokenController {

    private final ITokenDAO tokenDAO;

    private final TokenRepository tokenRepository;

    private final WTokenRepository wTokenRepository;

    @GetMapping("/n1/all")
    public List<Token> n1all() {
        return tokenRepository.n1findAll();
    }

    @GetMapping("/template/all")
    public List<Token> templateAll() {
        return wTokenRepository.findAll();
    }


    @PostMapping
    public void add(@Valid @RequestBody Token token) {
        token.setData("a123445678".getBytes(StandardCharsets.UTF_8));
        tokenDAO.insertToken(token);
    }

    @GetMapping
    public List<Token> all() {
        return tokenRepository.findAll();
    }

    @GetMapping("/{token}")
    public Token token(@PathVariable String token) {
        return tokenDAO.selectTokenByToken(token);
    }

    @GetMapping("/fetToken/{fetToken}")
    public Token fetToken(@PathVariable String fetToken) {
        return tokenDAO.selectTokenByFetToken(fetToken);
    }

    @GetMapping("/search")
    public Token search(
            @RequestParam Integer memberId,
            @RequestParam(required = false) String udid
    ) {
        return tokenDAO.selectTokenByMemberIdAndDeviceId(memberId, udid);
    }

    @GetMapping("/test")
    public List<UserTokenDto> test() {
        return tokenRepository.select(88);
    }
}

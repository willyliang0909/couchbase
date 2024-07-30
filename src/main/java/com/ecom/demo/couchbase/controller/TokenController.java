package com.ecom.demo.couchbase.controller;

import com.ecom.demo.couchbase.dao.ITokenDAO;
import com.ecom.demo.couchbase.model.Token;
import com.ecom.demo.couchbase.model.dto.UserTokenDto;
import com.ecom.demo.couchbase.repository.TokenRepository;
import com.ecom.demo.couchbase.repository.TokenScopeRepository;
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
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tokens")
public class TokenController {

    private final ITokenDAO tokenDAO;

    private final TokenRepository tokenRepository;

    private final TokenScopeRepository tokenScopeRepository;

    private final WTokenRepository wTokenRepository;

    @GetMapping("/scope/fetToken")
    public List<Token> scopeFetToken(@RequestParam String fetToken) {
        return tokenScopeRepository.withScope("seq1").findAllByFetToken(fetToken);
    }

    @GetMapping("/n1/all")
    public List<Token> n1all() {
        return tokenRepository.n1findAll();
    }

    @GetMapping("/template/all")
    public List<Token> templateAll() {
        return wTokenRepository.findAll();
    }

    @GetMapping("/template/{id}")
    public Token templateFindById(@PathVariable String id) {
        return wTokenRepository.findById(id);
    }

    @GetMapping("/template/{scope}/{id}")
    public Token templateFindByIdInScope(@PathVariable String scope, @PathVariable String id) {
        return wTokenRepository.findByIdInScope(scope, id);
    }

    @GetMapping("/template/ids")
    public Collection<? extends Token> templateFindById(@RequestParam Collection<String> ids) {
        return wTokenRepository.findByIds(ids);
    }

    @GetMapping("/template/idsAndFetToken")
    public Collection<? extends Token> templateFindByIdAnd(
            @RequestParam Collection<String> ids,
            @RequestParam String fetToken
    ) {
        return wTokenRepository.findByIdsInAndFetToken(ids, fetToken);
    }

    @GetMapping("/idsAndFetToken")
    public List<Token> findByIdAnd(
            @RequestParam List<String> tokens,
            @RequestParam String fetToken
    ) {
        return tokenRepository.findByTokenInAndFetToken(tokens, fetToken);
    }

    @PostMapping("/template/upsert")
    public Token upsert(@RequestBody Token token) {
        return wTokenRepository.upsert(token);
    }


    @PostMapping
    public Token add(@Valid @RequestBody Token token) {
        token.setData("a123445678".getBytes(StandardCharsets.UTF_8));
        return tokenDAO.insertToken(token);
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

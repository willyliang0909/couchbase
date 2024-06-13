package com.ecom.demo.couchbase.controller;

import com.couchbase.client.java.Collection;
import com.couchbase.client.java.codec.RawBinaryTranscoder;
import com.couchbase.client.java.kv.UpsertOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/config")
public class ConfigController {

    private final Collection configCollection;

    @Value("classpath:logo.png")
    private Resource resource;

    @GetMapping("/{id}")
    public Object config(String id) {
        var rs = configCollection.get(id).contentAsBytes();
        return rs;
    }

    @PostMapping("/{id}")
    public void save(@PathVariable String id) throws IOException {
        var options = UpsertOptions.upsertOptions().transcoder(RawBinaryTranscoder.INSTANCE);
        configCollection.upsert(id, resource.getInputStream().readAllBytes(), options);
    }
}

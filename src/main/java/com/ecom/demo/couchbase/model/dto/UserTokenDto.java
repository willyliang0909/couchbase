package com.ecom.demo.couchbase.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.repository.Scope;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Document
public class UserTokenDto {

    @Id
    private String token;
    private String scope;
    private Integer memberId;
    private String fetToken;

    private String name;
    private String email;
    private String deviceId;
}

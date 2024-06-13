package com.ecom.demo.couchbase.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    private String token;
    private String scope;
    private Integer memberId;
    private String fetToken;
    private String udid;
    private String platform;
    private String model;
    private String version;
    private String userAgent;
    private String code;
    private String serialNumber;
    private Date effectiveDateTime;
    private Date expireDateTime;
    private byte[] data;

}

package com.kentkart.api.line.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateLine_WC_MLS_Request {
    
    private String lineCode;
    private String lineCodeRepresentation;
}

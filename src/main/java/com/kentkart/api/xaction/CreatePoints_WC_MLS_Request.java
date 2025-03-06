package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePoints_WC_MLS_Request {
    
    private String routeId;

    private Integer sequence;

    private Double latitude;
    
    private Double longitude;
    
}

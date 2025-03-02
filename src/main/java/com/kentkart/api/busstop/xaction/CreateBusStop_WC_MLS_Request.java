package com.kentkart.api.busstop.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBusStop_WC_MLS_Request {
    
    private Double latitude;
    private Double longitude;
    private String stopName;
}

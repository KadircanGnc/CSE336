package com.kentkart.api.busstop.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.busstop.BusStop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBusStop_WC_MLS_Response {
    
    private String id;

    public CreateBusStop_WC_MLS_Response(BusStop busStop) {
        this.id = busStop.getId();
    }
}

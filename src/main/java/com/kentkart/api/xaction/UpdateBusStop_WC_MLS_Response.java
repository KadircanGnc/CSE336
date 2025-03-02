package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.busstop.BusStop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBusStop_WC_MLS_Response {
    
    private String id;

    public UpdateBusStop_WC_MLS_Response(BusStop busStop) {
        this.id = busStop.getId();
    }
}

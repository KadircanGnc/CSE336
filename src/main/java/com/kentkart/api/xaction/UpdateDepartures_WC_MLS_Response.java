package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.departure.Departure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDepartures_WC_MLS_Response {
    
    private String id;
    public UpdateDepartures_WC_MLS_Response(Departure departure) {
        this.id = departure.getId();
    }
}

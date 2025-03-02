package com.kentkart.api.departure.xaction.departureDay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.departure.DepartureDay;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDepartureDay_WC_MLS_Response {
    private String id;
    
    public CreateDepartureDay_WC_MLS_Response(DepartureDay departureDay) {
        this.id = departureDay.getId();
    }
}

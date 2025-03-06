package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.departure.Departure;
import com.kentkart.api.departure.DepartureDay;
import com.kentkart.api.route.Route;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDepartures_WC_MLS_Response {
    
    private String id;    
    private Route route;    
    private DepartureDay departureDay;
    
    public GetDepartures_WC_MLS_Response(Departure departure) {
        this.id = departure.getId();
        this.route = departure.getRoute();
        this.departureDay = departure.getDepartureDay();
    }
}

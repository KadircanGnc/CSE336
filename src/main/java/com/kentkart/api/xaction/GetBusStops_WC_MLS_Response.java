package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.busstop.BusStop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBusStops_WC_MLS_Response {

    private String id;
    private Double latitude;
    private Double longitude;
    private String stopName;

    public GetBusStops_WC_MLS_Response(BusStop busStop) {
        this.id = busStop.getId();
        this.latitude = busStop.getLatitude();
        this.longitude = busStop.getLongitude();
        this.stopName = busStop.getStopName();
    }
}

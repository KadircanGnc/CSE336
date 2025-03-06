package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.point.Point;
import com.kentkart.api.route.Route;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPoints_WC_MLS_Response {

    private String id;
    private Route route;
    private Integer sequence;
    private Double latitude;
    private Double longitude;

    public GetPoints_WC_MLS_Response(Point point) {
        this.id = point.getId();
        this.route = point.getRoute();
        this.sequence = point.getSequence();
        this.latitude = point.getLatitude();
        this.longitude = point.getLongitude();
    }
    
}

package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.point.Point;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePoints_WC_MLS_Response {
    private String id;

    public CreatePoints_WC_MLS_Response(Point point) {
        this.id = point.getId();
    }
    
}

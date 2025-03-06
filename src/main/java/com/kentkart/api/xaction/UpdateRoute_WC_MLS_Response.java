package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.route.Route;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRoute_WC_MLS_Response {
    
    private String id;

    public UpdateRoute_WC_MLS_Response(Route route) {
        this.id = route.getId();
    }
}

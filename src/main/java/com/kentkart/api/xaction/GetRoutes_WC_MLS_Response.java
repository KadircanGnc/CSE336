package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.direction.Direction;
import com.kentkart.api.line.Line;
import com.kentkart.api.route.Route;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRoutes_WC_MLS_Response {
    
    private String id;    
    private Direction direction;    
    private Line line;
    
    public GetRoutes_WC_MLS_Response(Route route) {
        this.id = route.getId();
        this.direction = route.getDirection();
        this.line = route.getLine();
    }
}

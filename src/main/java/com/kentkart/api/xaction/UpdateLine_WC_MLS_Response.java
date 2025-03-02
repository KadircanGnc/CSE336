package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.line.Line;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateLine_WC_MLS_Response {
    
    private String id;

    public UpdateLine_WC_MLS_Response(Line line) {
        this.id = line.getId();
    }
}

package com.kentkart.api.line.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.line.Line;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateLine_WC_MLS_Response {
    private String id;

    public CreateLine_WC_MLS_Response(Line line) {
        this.id = line.getId();
    }
}

package com.kentkart.api.line.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.line.Line;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLines_WC_MLS_Response {

    private String id;
    private String lineCode;
    private String lineCodeRepresentation;

    public GetLines_WC_MLS_Response(Line line) {
        this.id = line.getId();
        this.lineCode = line.getLineCode();
        this.lineCodeRepresentation = line.getLineCodeRepresentation();
    }
}

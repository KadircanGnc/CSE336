package com.kentkart.api.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDepartureDay_WC_MLS_Request {
    private String day;
}

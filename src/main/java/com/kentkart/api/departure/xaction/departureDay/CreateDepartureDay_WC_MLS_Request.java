package com.kentkart.api.departure.xaction.departureDay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDepartureDay_WC_MLS_Request {
    private String day;
}

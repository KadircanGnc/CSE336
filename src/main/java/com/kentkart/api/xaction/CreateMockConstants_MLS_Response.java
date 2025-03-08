package com.kentkart.api.xaction;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMockConstants_MLS_Response {

  private Set<String> boardingtypeids;

  private Set<String> busstopids;

  private Set<String> directionids;

  private Set<String> lineids;

  private Set<String> pointids;

  private Set<String> routeids;

  private Set<String> boardingids;

  private Set<String> departureids;

  private Set<String> departuredayids;
}

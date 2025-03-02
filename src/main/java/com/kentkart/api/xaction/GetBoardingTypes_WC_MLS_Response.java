package com.kentkart.api.xaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.boardingtype.BoardingType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBoardingTypes_WC_MLS_Response {
    
    private String id;
    private String name;

    public GetBoardingTypes_WC_MLS_Response(BoardingType boardingType) {
        this.id = boardingType.getId();
        this.name = boardingType.getName();
    }
    
}

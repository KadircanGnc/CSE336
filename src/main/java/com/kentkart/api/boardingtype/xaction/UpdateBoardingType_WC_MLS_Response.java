package com.kentkart.api.boardingtype.xaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kentkart.api.boardingtype.BoardingType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBoardingType_WC_MLS_Response {
            
    private String id;

    public UpdateBoardingType_WC_MLS_Response(BoardingType boardingType) {
        this.id = boardingType.getId();
    }
}

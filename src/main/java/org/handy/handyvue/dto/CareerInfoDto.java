package org.handy.handyvue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CareerInfoDto {
    @JsonProperty("vCareerId")
    private String vCareerId;

    @JsonProperty("vCareerNm")
    private String vCareerNm;

    @JsonProperty("dStartDtm")
    private String dStartDtm;

    @JsonProperty("dEndDtm")
    private String dEndDtm;

    private int totalMonths;
}

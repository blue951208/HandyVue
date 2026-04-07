package org.handy.handyvue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CareerInfoDto {
    private String vCareerId;

    @JsonProperty("vCareerNm") // JSON의 키값과 강제로 맞춤
    private String vCareerNm;

    @JsonProperty("dStartDtm")
    private String dStartDtm;

    @JsonProperty("dEndDtm")
    private String dEndDtm;

    private int totalMonths;
}

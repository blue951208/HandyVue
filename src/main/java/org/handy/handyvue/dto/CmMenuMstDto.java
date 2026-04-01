package org.handy.handyvue.dto;

import lombok.Data;

import java.util.List;

@Data
public class CmMenuMstDto {
    private String vMenuId;
    private String vParentMenuId;
    private String vMenuNm;
    private int nLevel;
    private String vUrl;
    private int nSort;

    private String vUseYn;
    private String vFlagDel;

    private Boolean isOpen; // 메뉴 트리에서 초반값 false 로 설정

    private List<CmMenuMstDto> children; // 자식 메뉴를 담을 리스트
}

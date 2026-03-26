package org.handy.handyvue.dto;

import lombok.Data;

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
}

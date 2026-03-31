package org.handy.handyvue.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.handy.handyvue.dto.CmMenuMstDto;

import java.util.List;

@Mapper
public interface CmMenuMstMapper {

    List<CmMenuMstDto> selectCmMenuList();

}

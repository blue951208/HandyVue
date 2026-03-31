package org.handy.handyvue.service.impl;

import org.handy.handyvue.dto.CmMenuMstDto;
import org.handy.handyvue.mapper.CmMenuMstMapper;
import org.handy.handyvue.service.CmMenuMstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CmMenuMstServiceImpl implements CmMenuMstService {

    @Autowired
    private CmMenuMstMapper cmMenuMstMapper;

    @Override
    public List<CmMenuMstDto> selectCmMenuList() {
        return cmMenuMstMapper.selectCmMenuList();
    }
}

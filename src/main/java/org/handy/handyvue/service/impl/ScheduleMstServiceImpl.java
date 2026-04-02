package org.handy.handyvue.service.impl;

import org.handy.handyvue.dto.ScheduleMstDto;
import org.handy.handyvue.mapper.ScheduleMstMapper;
import org.handy.handyvue.service.ScheduleMstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScheduleMstServiceImpl implements ScheduleMstService {

    @Autowired
    private ScheduleMstMapper scheduleMstMapper;

    @Override
    public List<ScheduleMstDto> selectScheduleMstList(Map<String, Object> params) {
        return scheduleMstMapper.selectScheduleMstList(params);
    }
}

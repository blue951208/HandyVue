package org.handy.handyvue.mapper;

import org.handy.handyvue.dto.ScheduleMstDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ScheduleMstMapper {

    List<ScheduleMstDto> selectScheduleMstList(Map<String, Object> param);

}
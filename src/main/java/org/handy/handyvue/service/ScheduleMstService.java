package org.handy.handyvue.service;

import org.handy.handyvue.dto.ScheduleMstDto;

import java.util.List;
import java.util.Map;

public interface ScheduleMstService {
    List<ScheduleMstDto> selectScheduleMstList(Map<String, Object> params);
}

package org.handy.handyvue.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.handy.handyvue.dto.CareerInfoDto;

import java.util.List;

@Mapper
public interface CareerInfoMapper {
    List<CareerInfoDto> selectCareerInfoList();
}

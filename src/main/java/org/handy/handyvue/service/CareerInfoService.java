package org.handy.handyvue.service;

import org.handy.handyvue.dto.CareerInfoDto;

import java.util.List;

public interface CareerInfoService {
    List<CareerInfoDto> selectCareerInfoList();

    CareerInfoDto selectCareerInfoDtl(CareerInfoDto careerInfoDto);

    void insertCareerInfo(CareerInfoDto careerInfoDto);

    void updateCareerInfo(CareerInfoDto careerInfoDto);

    void deleteCareerInfo(CareerInfoDto careerInfoDto);
}

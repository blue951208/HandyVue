package org.handy.handyvue.service.impl;

import org.handy.handyvue.dto.CareerInfoDto;
import org.handy.handyvue.mapper.CareerInfoMapper;
import org.handy.handyvue.service.CareerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerInfoServiceImpl implements CareerInfoService {

    @Autowired
    private CareerInfoMapper careerInfoMapper;

    @Override
    public List<CareerInfoDto> selectCareerInfoList() {
        return careerInfoMapper.selectCareerInfoList();
    }

    @Override
    public void insertCareerInfo(CareerInfoDto careerInfoDto) {
        careerInfoMapper.insertCareerInfo(careerInfoDto);
    }

    @Override
    public void updateCareerInfo(CareerInfoDto careerInfoDto) {
        careerInfoMapper.updateCareerInfo(careerInfoDto);
    }
}

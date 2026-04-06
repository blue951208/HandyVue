package org.handy.handyvue.controller;

import org.handy.handyvue.dto.CareerInfoDto;
import org.handy.handyvue.service.CareerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/career")
@CrossOrigin(origins = "http://localhost:5173")
public class CareerInfoController {
    @Autowired
    private CareerInfoService careerInfoService;

    @GetMapping("/list")
    public List<CareerInfoDto> selectCareerInfoList() {
        System.out.println("selectCmMenuList called");
        List<CareerInfoDto> list = careerInfoService.selectCareerInfoList();
        return list;
    }
}

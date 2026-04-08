package org.handy.handyvue.controller;

import org.handy.handyvue.dto.CareerInfoDto;
import org.handy.handyvue.service.CareerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/detail")
    public CareerInfoDto selectCareerInfoDtl(CareerInfoDto careerInfoDto) {
        System.out.println("selectCareerInfoDtl called");
        CareerInfoDto info = careerInfoService.selectCareerInfoDtl(careerInfoDto);
        return info;
    }

    @PostMapping("/insert")
    @ResponseBody
    public Map<String, Object> insertCareerInfo(@RequestBody CareerInfoDto careerInfoDto) {
        System.out.println("insertCareerInfo called with: " + careerInfoDto);
        Map<String, Object> result = new HashMap<>();
        try {
            careerInfoService.insertCareerInfo(careerInfoDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
            System.out.println("Error inserting career info: " + e.getMessage());
        }

        return result;
    }

    @PostMapping("/update")
    public Map<String, Object> updateCareerInfo(@RequestBody CareerInfoDto careerInfoDto) {
        System.out.println("updateCareerInfo called with: " + careerInfoDto);
        Map<String, Object> result = new HashMap<>();
        try {
            careerInfoService.updateCareerInfo(careerInfoDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
            System.out.println("Error update career info: " + e.getMessage());
        }

        return result;
    }

    @GetMapping("/delete")
    public Map<String, Object> deleteCareerInfo(CareerInfoDto careerInfoDto) {
        System.out.println("deleteCareerInfo called with: " + careerInfoDto);
        Map<String, Object> result = new HashMap<>();
        try {
            careerInfoService.deleteCareerInfo(careerInfoDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
            System.out.println("Error delete career info: " + e.getMessage());
        }

        return result;
    }
}

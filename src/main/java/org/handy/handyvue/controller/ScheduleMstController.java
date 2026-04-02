package org.handy.handyvue.controller;

import org.handy.handyvue.dto.ScheduleMstDto;
import org.handy.handyvue.service.ScheduleMstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scheduleMst")
@CrossOrigin(origins = "http://localhost:5173")
public class ScheduleMstController {

    @Autowired
    private ScheduleMstService scheduleMstService;

    @GetMapping("/list")
    public List<ScheduleMstDto> selectScheduleMstList(@RequestParam Map<String, Object> param) {
        List<ScheduleMstDto> list = scheduleMstService.selectScheduleMstList(param);
        System.out.println("list : "+ list);
        return list;
    }

}

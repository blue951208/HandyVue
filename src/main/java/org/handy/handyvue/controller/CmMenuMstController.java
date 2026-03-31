package org.handy.handyvue.controller;

import org.handy.handyvue.dto.CmMenuMstDto;

import org.handy.handyvue.service.CmMenuMstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cmMenuMst")
@CrossOrigin(origins = "http://localhost:5173")
public class CmMenuMstController {

    @Autowired
    private CmMenuMstService cmMenuMstService;

    @GetMapping("/list")
    public List<CmMenuMstDto> selectCmMenuList() {
        System.out.println("selectCmMenuList called");
        return cmMenuMstService.selectCmMenuList();
    }
}

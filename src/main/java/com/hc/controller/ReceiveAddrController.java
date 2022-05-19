package com.hc.controller;

import com.hc.domain.ReceiveAddr;
import com.hc.domain.vo.ReceiveAddrVO;
import com.hc.service.ReceiveAddrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/receiveAddr")
public class ReceiveAddrController {
    @Resource
    private ReceiveAddrService receiveAddrService;

    @GetMapping("/list/{userId}")
    public ModelAndView list(@PathVariable("userId") Long userId, ModelAndView mav) {
        List<ReceiveAddrVO> list = receiveAddrService.selectByUserId(userId);
        mav.addObject("receiveAddrVOList",list);
        mav.setViewName("receiveAddr_list");
        return mav;
    }

    @GetMapping("/get/{id}")
    public ModelAndView getById(@PathVariable Long id, ModelAndView mav) {
        ReceiveAddr receiveAddr = receiveAddrService.selectByPrimaryKey(id);
        mav.addObject("receiveAddr", receiveAddr);
        mav.setViewName("receiveAddr_details");
        return mav;
    }

    @PostMapping
    public ModelAndView create(@RequestBody ReceiveAddr receiveAddr, ModelAndView mav) {
        receiveAddrService.insert(receiveAddr);
        mav.setViewName("receiveAddr_list");
        return mav;
    }

    @PutMapping
    public ModelAndView updateById(@RequestBody ReceiveAddr receiveAddr, ModelAndView mav) {
        receiveAddrService.updateByPrimaryKeySelective(receiveAddr);
        mav.setViewName("receiveAddr_list");
        return mav;
    }

    @DeleteMapping("/{id}")
    public ModelAndView removeById(@PathVariable Long id, ModelAndView mav) {
        receiveAddrService.deleteByPrimaryKey(id);
        mav.setViewName("receiveAddr_list");
        return mav;
    }
}
package cn.o7si.controller;

import cn.o7si.service.IHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 股票持有情况的控制层
 */
@RequestMapping("/hold")
@Controller("holdController")
public class HoldController {

    @Autowired
    private IHoldService holdService;
}

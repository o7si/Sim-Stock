package cn.o7si.controller;

import cn.o7si.sim.MarketSim;
import cn.o7si.vo.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller("test")
public class Test {

    @RequestMapping("/start")
    public @ResponseBody
    ResponseData start() {
        return null;
    }
}

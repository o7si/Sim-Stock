package cn.o7si.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Test {

    @RequestMapping(value = "/helloworld", method = RequestMethod.POST)
    public @ResponseBody Message testHelloWorld(@RequestBody Message message) {
        System.out.println(message);
        message.setMsg("HelloWorld");
        return message;
    }
}

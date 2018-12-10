package guru.springframework.controllers;

import guru.springframework.common.en.ExceptionEnum;
import guru.springframework.common.exception.DescribeException;
import guru.springframework.common.response.Result;
import guru.springframework.common.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/")
    String index(){
        return "index";
    }


}

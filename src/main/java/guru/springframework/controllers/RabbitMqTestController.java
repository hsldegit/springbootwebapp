package guru.springframework.controllers;

import guru.springframework.common.response.Result;
import guru.springframework.common.util.ResultUtil;
import guru.springframework.rabbitmq.MqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangshilu
 * @date 2018/12/10 14:15
 * @description
 */
@RestController
@RequestMapping("/rabbitMqTest")
public class RabbitMqTestController {

    @Autowired
    private MqSender mqSender;

    @RequestMapping(value = "/sendMsg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result sendMsg(String msg) {
        mqSender.sendMsg("exchange", "keyA", msg);
        return ResultUtil.success();
    }


}

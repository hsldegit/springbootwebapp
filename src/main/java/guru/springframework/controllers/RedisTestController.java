package guru.springframework.controllers;

import guru.springframework.common.response.Result;
import guru.springframework.common.util.ResultUtil;
import guru.springframework.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangshilu
 * @date 2019/2/22 16:08
 * @description
 */
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result save(String key, String value) {
        redisUtil.setKey(key, value);
        return ResultUtil.success();
    }
}

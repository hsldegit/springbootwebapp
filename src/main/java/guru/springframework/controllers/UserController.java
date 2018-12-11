package guru.springframework.controllers;

import guru.springframework.common.en.ExceptionEnum;
import guru.springframework.common.exception.DescribeException;
import guru.springframework.common.request.User;
import guru.springframework.common.response.Result;
import guru.springframework.common.util.BeanUtil;
import guru.springframework.common.util.ResultUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.System.out;

/**
 * @author huangshilu
 * @date 2018/12/10 14:15
 * @description
 */
@RestController
@RequestMapping("/result")
public class UserController {


    /**
     * @param user
     * @return
     */
    @RequestMapping(value = "/getResult", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result getResult(@RequestBody User user) {
        long start = System.currentTimeMillis();
        Result result = ResultUtil.success();

        if (user.getName().equals("zzp")) {
            result = ResultUtil.success();
        } else if (user.getName().equals("pzz")) {
            result = ResultUtil.error(ExceptionEnum.USER_NOT_FIND);
        } else if (user.getName().equals("aaa")) {
            int i = 1 / 0;
        } else {
            throw new DescribeException("自定义异常", 000001);
        }
        long end = System.currentTimeMillis();
        out.println("test0总共耗时:" + (end - start) + "ms");
        return result;
    }


//    @RequestMapping(value = "/getResult")
//    public Result getResult(@RequestParam(value = "name",required = false)String name ,@RequestParam(value = "pwd",required = false)String pwd) {
//        Result result = ResultUtil.success();
//
//        if (name.equals("zzp")) {
//            result = ResultUtil.success();
//        } else if (name.equals("pzz")) {
//            result = ResultUtil.error(ExceptionEnum.USER_NOT_FIND);
//        } else if (name.equals("aaa")) {
//            int i = 1 / 0;
//        } else {
//            throw new DescribeException("自定义异常", 000001);
//        }
//        return result;
//    }

    @RequestMapping(value = "test1")
    public Result test1() {
        User user = new User();
        user.setName("aaa");
        user.setPwd("bbb");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            User user1 = new User();
            user1.setName(user.getName());
            user1.setPwd(user.getPwd());
        }
        long end = System.currentTimeMillis();
        out.println("test1总共耗时:" + (end - start) + "ms");
        return ResultUtil.success();
    }


    @RequestMapping(value = "test2")
    public Result test2() {
        User user = new User();
        user.setName("aaa");
        user.setPwd("bbb");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            User user1 = BeanUtil.copyProperties(user, User.class);
        }
        long end = System.currentTimeMillis();
        out.println("test2总共耗时:" + (end - start) + "ms");
        return ResultUtil.success();
    }

}

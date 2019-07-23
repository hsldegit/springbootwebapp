package guru.springframework.controllers;

import guru.springframework.common.en.ExceptionEnum;
import guru.springframework.common.exception.DescribeException;
import guru.springframework.common.request.User;
import guru.springframework.common.response.Result;
import guru.springframework.common.thread.CallableTest;
import guru.springframework.common.util.BeanUtil;
import guru.springframework.common.util.NumUtil;
import guru.springframework.common.util.ResultUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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
        System.out.println("test0总共耗时:" + (end - start) + "ms");
        return result;
    }


//    @RequestMapping(value = "/getResult")
//    public Result getResult(@RequestParam(value = "name",required = false)String name ,@RequestParam(value = "pwd",
// required = false)String pwd) {
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
    public static Result test1() {
        User user = new User();
        user.setName("aaa");
        user.setPwd("bbb");
        long start = System.currentTimeMillis();
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User user1 = new User();
            user1.setName(user.getName());
            user1.setPwd(user.getPwd());
            list.add(user1);
        }
        long end = System.currentTimeMillis();
        System.out.println("test1总共耗时:" + (end - start) + "ms");
        return ResultUtil.success(list);
    }


    @RequestMapping(value = "test2")
    public Result test2() {
        User user = new User();
        user.setName("aaa");
        user.setPwd("bbb");
        long start = System.currentTimeMillis();
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User user1 = BeanUtil.copyProperties(user, User.class);
            list.add(user1);
        }
        long end = System.currentTimeMillis();
        System.out.println("test2总共耗时:" + (end - start) + "ms");
        return ResultUtil.success(list);
    }

    /**
     * 多线程提高效率处理数据返回.
     *
     * @param a
     * @return
     */
    @RequestMapping(value = "test3")
    public Result test3(String a) {
        long start = System.currentTimeMillis();
        String[] arr = a.split(",");
        List<Double> result = new ArrayList<>();
        List<Callable<Double>> tasks = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            Callable<Double> qfe = new CallableTest(Double.parseDouble(arr[i]));
            tasks.add(qfe);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        try {
            List<Future<Double>> futures = executorService.invokeAll(tasks);
            if (futures != null && futures.size() > 0) {
                for (Future<Double> future : futures) {
                    result.add(future.get());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();//关闭线程池
        long end = System.currentTimeMillis();
        System.out.println("test3总共耗时:" + (end - start) + "ms");

        String s = new String("11111");
        return ResultUtil.success(result);
    }


    @RequestMapping(value = "test4")
    public Result test4(String a) {
        long start = System.currentTimeMillis();
        String[] arr = a.split(",");
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            result.add(NumUtil.getDoubleResult(Double.parseDouble(arr[i])));
        }
        long end = System.currentTimeMillis();
        System.out.println("test4总共耗时:" + (end - start) + "ms");
        return ResultUtil.success(result);
    }

    @RequestMapping(value = "test5")
    public Result test5(String a) {
        long start = System.currentTimeMillis();
        String[] arr = a.split(",");
        List<String> params = Arrays.asList(arr);
        List<Double> result = new ArrayList<>();
        result.addAll(params.parallelStream().map(e -> NumUtil.getDoubleResult(Double.parseDouble(e))).collect(Collectors.toList()));
        //result.addAll(params.stream().map(e -> NumUtil.getDoubleResult(Double.parseDouble(e))).collect(Collectors
        // .toList()));
        long end = System.currentTimeMillis();
        System.out.println("test5总共耗时:" + (end - start) + "ms");
        return ResultUtil.success(result);
    }

}

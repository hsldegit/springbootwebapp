package guru.springframework.controllers;

import guru.springframework.common.exception.StockVersionErrorException;
import guru.springframework.common.response.Result;
import guru.springframework.common.util.ResultUtil;
import guru.springframework.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangshilu
 * @date 2019/3/7 15:16
 * @description
 */
@Controller
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/stock/up1")
    public Result updateStock1() {
        try {
            stockService.updateStock1(4L, -1);
        } catch (RuntimeException e) {
            System.out.println(e);
            return ResultUtil.error(10001, e.getMessage());
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/stock/up2")
    public Result updateStock2() {
        try {
            stockService.updateStock2(4L, -1);
        } catch (RuntimeException e) {
            return ResultUtil.error(10001, e.getMessage());
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/stock/upv1")
    public Result updateStockWithVersion1() {
        try {
            for (int i = 0; i < 3; i++) {
                int result = stockService.updateStockWithVersion1(4L, -1);
                if (result == 0) {
                    return ResultUtil.success();
                }
            }
        } catch (RuntimeException e) {
            return ResultUtil.error(10001, e.getMessage());
        }
        return ResultUtil.error(2111, "更新库存失败");
    }

    @RequestMapping(value = "/stock/upv2")
    public Result updateStockWithVersion2() {
        try {
            for (int i = 0; i < 3; i++) {
                int result = stockService.updateStockWithVersion2(4L, -1);
                if (result == 0) {
                    return ResultUtil.success();
                }
            }
        } catch (RuntimeException e) {
            return ResultUtil.error(10001, e.getMessage());
        }
        return ResultUtil.error(2111, "更新库存失败");
    }

    @RequestMapping(value = "/stock/test")
    public Result test() {
        try {
            stockService.test(4L);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.success();
    }

}

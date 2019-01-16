package guru.springframework.common.thread;

import guru.springframework.common.util.NumUtil;

import java.util.concurrent.Callable;

/**
 * @author huangshilu
 * @date 2019/1/15 10:43
 * @description
 */
public class CallableTest implements Callable {

    private Double src;

    public CallableTest(Double src) {
        this.src = src;
    }

    @Override
    public Double call() {
        return NumUtil.getDoubleResult(src);
    }


}

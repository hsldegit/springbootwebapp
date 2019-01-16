package guru.springframework.common.util;

/**
 * @author huangshilu
 * @date 2019/1/15 09:52
 * @description
 */
public class NumUtil {


    public static Double getDoubleResult(Double src) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return src * 2;
    }


}

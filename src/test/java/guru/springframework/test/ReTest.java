package guru.springframework.test;

import guru.springframework.common.request.User;
import guru.springframework.common.util.BeanUtil;

import static java.lang.System.out;

/**
 * @author huangshilu
 * @date 2018/12/10 17:44
 * @description
 */
public class ReTest {

    public static void main(String[] args) {
        User user = new User();
        user.setName("aaa");
        user.setPwd("bbb");
        //test1(user);
        test2(user);
    }


    public static void test1(User user) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            User user1 = new User();
            user1.setName(user.getName());
            user1.setPwd(user.getPwd());
        }
        long end = System.currentTimeMillis();
        out.println("test1总共耗时:" + (end - start) + "ms");
    }

    public static void test2(User user) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            User user1 = BeanUtil.copyProperties(user, User.class);
        }
        long end = System.currentTimeMillis();
        out.println("test2总共耗时:" + (end - start) + "ms");

    }


}

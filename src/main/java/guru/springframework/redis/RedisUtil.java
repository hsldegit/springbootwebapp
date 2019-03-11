package guru.springframework.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author huangshilu
 * @date 2019/2/22 15:57
 * @description
 */
@Service
public class RedisUtil {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    public void setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }


}

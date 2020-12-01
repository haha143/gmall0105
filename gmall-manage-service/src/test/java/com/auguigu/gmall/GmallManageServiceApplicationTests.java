package com.auguigu.gmall;

import com.auguigu.gmall.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource(value={"classpath:application.properties"})
@SpringBootApplication()
public class GmallManageServiceApplicationTests {
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void contextLoads(){
        Jedis jedis=redisUtil.getJedis();
        System.out.println(jedis);
    }
}

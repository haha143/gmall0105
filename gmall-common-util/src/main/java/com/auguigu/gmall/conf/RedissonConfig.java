package com.auguigu.gmall.conf;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    //读取配置文件中的redis的ip地址.端口号,数据库,密码

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.database:0}")
    private int database;

    @Value("${spring.redis.password:@Yzr143253}")
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        //链式编程
        config.useSingleServer().setAddress("redis://" + host + ":" + port)
                                .setPassword(password)
                                .setDatabase(database);
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}

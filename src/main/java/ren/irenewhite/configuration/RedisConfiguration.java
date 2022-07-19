package ren.irenewhite.configuration;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ren.irenewhite.redis.RedisService;
import ren.irenewhite.redis.RedisManager;

/**
 * @author DearAhri520
 * @date 2022/6/1
 */

@EnableCaching
@Configuration
public class RedisConfiguration {
    /**
     * 配置redisTemplate针对不同key和value场景下不同序列化的方式
     *
     * @param factory Redis连接工厂
     * @return redisTemplate
     */
    @Primary
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        FastJsonRedisSerializer<Object> redisSerializer = new FastJsonRedisSerializer<>(Object.class);
        template.setValueSerializer(redisSerializer);
        template.setHashValueSerializer(redisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    RedisManager cache(RedisTemplate<String, Object> redisTemplate) {
        return new RedisService(redisTemplate);
    }
}

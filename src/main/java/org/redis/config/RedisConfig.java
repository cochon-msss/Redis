package org.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 환경 설정
 */
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    // redis 연결을 위한 'Connection' 생성
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(host, port);
    }

    // redis 데이터 처리를 위한 템플릿 구성
    // 해당 구성된 redis 템플릿을 통해서 데이터 통신 직렬화 수행
    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // redis 연결
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        // key-value 형태로 직렬화 수행
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        // hash key-value 형태로 직렬화 수행
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        // 기본적으로 직렬를 수행
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    // 리스트에 접근해서 다양한 연산을 수행
    public ListOperations<String, Object> getListOperations(){
        return this.redisTemplate().opsForList();
    }

    // 단일 데이터에 접근하여 다양한 연산을 수행
    public ValueOperations<String, Object> getValueOperations(){
        return this.redisTemplate().opsForValue();
    }

    // redis 작업중 등록, 수정, 삭제에 대해서 처리 및 예외처리를 수행합니다.
    public int executeOperation(Runnable operation){
        try{
            operation.run();
            return 1;
        }catch (Exception exception){
            System.out.println("Redis 작업 오류 발생 :: " + exception.getMessage());
            return 0;
        }
    }
}

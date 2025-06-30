package org.redis.service.impl;

import lombok.RequiredArgsConstructor;
import org.redis.config.RedisConfig;
import org.redis.handler.RedisHandler;
import org.redis.service.RedisSingleDataService;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisSingleDataServiceImpl implements RedisSingleDataService {

    private final RedisHandler redisHandler;
    private final RedisConfig redisConfig;

    // redis 단일 값을 등록/수정합니다.
    @Override
    public int setSingleData(String key, Object value){
        return redisHandler.executeOperation(() -> redisHandler.getValueOperations().set(key, value));
    }

    // redis 단일 값을 등록/수정합니다. (duration 값이 존재하면 메모리 유효시간 지정)
    @Override
    public int setSingleData(String key, Object value, Duration duration){
        return redisHandler.executeOperation(() -> redisHandler.getValueOperations().set(key, value, duration));
    }

    // redis 키를 기반으로 단일 데이터 조회
    @Override
    public String getSingleData(String key){
        if(redisHandler.getValueOperations().get(key) == null) return "";
        return String.valueOf(redisHandler.getValueOperations().get(key));
    }

    // redis 키를 기반으로 단일 데이터 삭제
    @Override
    public int deleteSingleData(String key){
        return redisHandler.executeOperation(() -> redisConfig.redisTemplate().delete(key));
    }


}

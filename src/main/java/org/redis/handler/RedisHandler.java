package org.redis.handler;

import lombok.RequiredArgsConstructor;
import org.redis.config.RedisConfig;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisHandler {

    private final RedisConfig redisConfig;

    // 리스트에 접근하여 다양한 연산을 수행
    public ListOperations<String, Object> getListOperations(){
        return redisConfig.getListOperations();
    }

    // 단일 데이터에 접근하여 다양한 연산을 수행
    public ValueOperations<String, Object> getValueOperations(){
        return redisConfig.getValueOperations();
    }

    // redis 작업중 등록, 수정, 삭제 처리 및 예외처리
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

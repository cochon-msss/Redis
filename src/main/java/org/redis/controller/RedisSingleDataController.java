package org.redis.controller;

import org.redis.dto.RedisDto;
import org.redis.service.RedisSingleDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// redis 단일 데이터를 조회, 등록, 삭제하는 로직
@RestController
@RequestMapping("/api/v1/redis/singleData")
public class RedisSingleDataController {

    private final RedisSingleDataService redisSingleDataService;

    public RedisSingleDataController(RedisSingleDataService redisSingleDataService){
        this.redisSingleDataService = redisSingleDataService;
    }

    // 단일 데이터 값 조회
    @PostMapping("/getValue")
    public ResponseEntity<Object> getValue(@RequestBody RedisDto redisDto){
        String result = redisSingleDataService.getSingleData(redisDto.getKey());
        return ResponseEntity.ok()
                .body(result);
    }

    // 단일 데이터 값 등록/수정 (duration 값이 존재하면 메모리 유효시간 지정)
    @PostMapping("/setValue")
    public ResponseEntity<Object> setValue(@RequestBody RedisDto redisDto){
        int result = 0;
        if(redisDto.getDuration() == null){
            result = redisSingleDataService.setSingleData(redisDto.getKey(), redisDto.getValue());
        }else{
            result = redisSingleDataService.setSingleData(redisDto.getKey(), redisDto.getValue(), redisDto.getDuration());
        }
        return ResponseEntity.ok()
                .body(result);
    }

    // 단일 데이터 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRow(@RequestBody RedisDto redisDto){
        int result = redisSingleDataService.deleteSingleData(redisDto.getKey());
        return ResponseEntity.ok()
                .body(result);
    }




}

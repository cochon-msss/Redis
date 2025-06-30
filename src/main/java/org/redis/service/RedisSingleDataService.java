package org.redis.service;

import org.springframework.stereotype.Service;

import java.time.Duration;

// 단일 데이터 처리 비즈니스 로직
@Service
public interface RedisSingleDataService {
    int setSingleData(String key, Object value); // 단일 데이터 값을 등록/ 수정 한다.
    int setSingleData(String key, Object value, Duration duration); // 단일 데이터 값을 등록, 수정한다. (duration 값이 존재하면 메모리 상 유효시간 지정)
    String getSingleData(String key); // 데이터 조회
    int deleteSingleData(String key); // 데이터 삭제

}

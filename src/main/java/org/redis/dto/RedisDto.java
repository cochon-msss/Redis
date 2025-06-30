package org.redis.dto;

import lombok.Getter;

import java.time.Duration;

@Getter
public class RedisDto {
    private String key;
    private Object value;
    private Duration duration;
}

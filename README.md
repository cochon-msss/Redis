# Redis

Mac OS에서 설치

## homebrew 설치 확인
brew --version

## redis 설치
brew install redis

## redis 설치 확인
redis-server --version

## fg 실행
redis-server

## bg 실행
brew services [start|restart|stop] redis

## 실행 상태 확인
brew services info redis

## cli 접근
redis-cli --raw (raw utf-8적용)


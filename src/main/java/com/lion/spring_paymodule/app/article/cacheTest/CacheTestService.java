package com.lion.spring_paymodule.app.article.cacheTest;

import com.lion.spring_paymodule.app.article.cacheTest.dto.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheTestService {
    @Cacheable("key1")
    public int getCachedInt() {
        System.out.println("getCachedInt 호출됨");
        return 5;
    }

    @CacheEvict(value = "key1")
    public void clearCacheKey1() {
        System.out.println("key1 캐시 삭제");
    }

    @CachePut(value="key1")
    public int modifyCacheKey1() {
        System.out.println("key1캐시 데이터 수정");
        return 10;
    }

    @Cacheable("plus")
    public int plus(int a, int b) {
        System.out.println("== plus 실행 ==");
        return a + b;
    }


    @Cacheable(value = "getName", key = "#p.id")
    public String getName(Person p, int random) {
        System.out.println("== getName 실행됨 ==");
        return p.getName();
    }
}

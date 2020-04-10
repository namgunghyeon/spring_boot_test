package com.example.demo;

import com.example.demo.config.EmbeddedRedisConfig;
import com.example.demo.config.LocalRedisClient;
import com.example.demo.service.TestService;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
public class RedisTests {

    @Autowired
    private TestService testService;

    @MockBean(name = "redisTemplateString")
    private RedisTemplate<String, String> redisTemplateString;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @MockBean(name = "redisTemplateObject")
    private RedisTemplate<String, Object> redisTemplateObject;

    @Mock
    private ValueOperations<String, Object> valueOperationObject;

    @MockBean
    private LocalRedisClient localRedisClient;

    @Mock
    private StatefulRedisConnection<String, String> statefulRedisConnection;

    @Mock
    private RedisAsyncCommands<String, String> redisAsyncCommands;

    @MockBean
    private RedisFuture<String> redisFuture;

    @BeforeAll
    static void start () {
        EmbeddedRedisConfig.start();
    }

    @AfterAll
    static void stop () {
        EmbeddedRedisConfig.stop();
    }

    @Test
    void get1Test() throws ExecutionException, InterruptedException, TimeoutException {
        String returnValue = "returnValue";
        Mockito.when(statefulRedisConnection.async()).thenReturn(redisAsyncCommands);
        Mockito.when(localRedisClient.getConnection()).thenReturn(statefulRedisConnection);
        Mockito.when(redisAsyncCommands.get(Mockito.anyString())).thenReturn(redisFuture);
        Mockito.when(redisFuture.get(Mockito.anyLong(), Mockito.any(TimeUnit.class))).thenReturn(returnValue);

        String ret = testService.get("test");
        Assertions.assertEquals("returnValue", ret);
    }

    @Test
    void get2Test() {
        String returnValue = "returnValue";
        Mockito.when(redisTemplateString.opsForValue()).thenReturn(valueOperations);
        Mockito.when(valueOperations.get(Mockito.anyString())).thenReturn(returnValue);

        String ret = testService.get2("test");
        Assertions.assertEquals("returnValue", ret);
    }

    @Test
    void get3Test() {
        Object returnValue = "returnValue";
        Mockito.when(redisTemplateObject.opsForValue()).thenReturn(valueOperationObject);
        Mockito.when(valueOperationObject.get(Mockito.anyString())).thenReturn(returnValue);

        Object ret = testService.get3("test");
        Assertions.assertEquals("returnValue", (String) ret);
    }
}

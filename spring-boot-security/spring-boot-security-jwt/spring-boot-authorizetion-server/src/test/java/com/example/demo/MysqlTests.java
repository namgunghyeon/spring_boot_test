package com.example.demo;

import com.example.demo.repository.UserRepository;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.Sources;
import com.wix.mysql.SqlScriptSource;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Version;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static java.lang.String.format;
import java.net.URL;

@SpringBootTest
public class MysqlTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void mysqlTest() {
        userRepository.findByUsername("1");
    }
}

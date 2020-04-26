package com.example.demo;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.Sources;
import com.wix.mysql.SqlScriptSource;
import com.wix.mysql.config.MysqldConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.SocketUtils;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import java.net.URL;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_19;
import static com.wix.mysql.distribution.Version.v8_latest;
import static java.lang.String.format;

//@Configuration
public class EmbeddedMysqlConfig {
    @Autowired
    EmbeddedMysql embeddedMysql;

    @Bean
    protected EmbeddedMysql embeddedMysql() {
        System.out.println("start embeddedMysql");
        EmbeddedMysql mysqld;
        MysqldConfig config = aMysqldConfig(v8_latest)
                .withCharset(UTF8)
                .withPort(SocketUtils.findAvailableTcpPort())
                .withUser("hyeon", "hyeon")
                .build();

        mysqld = anEmbeddedMysql(config)
                .addSchema("hyeon", classPathScript("db/001_init.sql"))
                .start();

        return mysqld;
    }

    @Bean
    @Autowired
    public DataSource dataSource(EmbeddedMysql embeddedMysql) {
        MysqldConfig mysqldConfig = embeddedMysql.getConfig();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://localhost:%d/%s?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true", mysqldConfig.getPort(), "hyeon"));
        dataSource.setUsername("hyeon");
        dataSource.setPassword("hyeon");

        return dataSource;
    }


    @PreDestroy
    protected void stopEmbeddedDatabase() {
        embeddedMysql.stop();
    }

    public static SqlScriptSource classPathScript(final String path) {
        String normalizedPath = path.startsWith("/") ? path : format("/%s", path);
        URL resource = ScriptResolver.class.getResource(normalizedPath);

        if (resource == null)
            throw new ScriptResolutionException(normalizedPath);

        return Sources.fromURL(resource);
    }

    public static class ScriptResolutionException extends RuntimeException {
        ScriptResolutionException(final String path) {
            super(format("No script(s) found for path '%s'", path));
        }
    }
}

package br.com.letscode.star.wars.config;

import br.com.letscode.star.wars.StarWarsApplication;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ContextConfiguration(classes = {StarWarsApplication.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
@Configuration
public class DbTestBase {

    @Value("classpath:schema.sql")
    Resource schema;

    @PostConstruct
    void init() throws SQLException {
        Connection connection = dataSource().getConnection();
        ScriptUtils.executeSqlScript(connection, schema);
    }

    @Bean
    DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:mem:db", "sa", "");
    }
}


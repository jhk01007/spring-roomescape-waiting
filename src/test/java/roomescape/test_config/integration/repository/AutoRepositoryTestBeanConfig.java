package roomescape.test_config.integration.repository;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import roomescape.test_config.integration.AutoJdbcTestBeans;

@TestConfiguration(proxyBeanMethods = false)
public class AutoRepositoryTestBeanConfig {

    @Bean
    static AutoJdbcTestBeans autoRepositoryTestBeans() {
        return AutoJdbcTestBeans.repositoryTestBeans();
    }
}

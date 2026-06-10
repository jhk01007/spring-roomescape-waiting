package roomescape.test_config.integration.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import roomescape.test_config.integration.AutoJdbcTestBeans;

@TestConfiguration(proxyBeanMethods = false)
class AutoServiceTestBeanConfig {

    @Bean
    static AutoJdbcTestBeans autoServiceTestBeans() {
        return AutoJdbcTestBeans.serviceTestBeans();
    }
}

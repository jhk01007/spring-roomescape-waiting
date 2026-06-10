package roomescape.test_config.integration.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration(proxyBeanMethods = false)
class AutoMockControllerDependencyConfig {

    @Bean
    static AutoWebMvcTestMockBeans autoMockControllerDependencies() {
        return new AutoWebMvcTestMockBeans();
    }
}

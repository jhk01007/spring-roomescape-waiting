package roomescape.test_config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration(proxyBeanMethods = false)
class AutoMockControllerDependencyConfig {

    @Bean
    static AutoMockControllerDependencies autoMockControllerDependencies() {
        return new AutoMockControllerDependencies();
    }
}

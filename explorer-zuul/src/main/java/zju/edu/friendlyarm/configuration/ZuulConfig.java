package zju.edu.friendlyarm.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @author xhzhao
 */
@Configuration
@Slf4j
public class ZuulConfig {

    @RefreshScope
    @Bean
    public FilterRegistrationBean corsFilter() {
        CorsConfiguration configuration = buildCorsConfig();
        log.info("Allowed origins are : {}", configuration.getAllowedOrigins());
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setName("zuulCorsFilter");
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    /**
     * 跨域配置
     * @return 跨域配置
     */
    private CorsConfiguration buildCorsConfig() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.addAllowedOrigin(CorsConfiguration.ALL);
        configuration.setExposedHeaders(Arrays.asList("Header1", "Header2"));
        return configuration;
    }
}

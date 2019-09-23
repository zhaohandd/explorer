package zju.edu.friendlyarm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xhzhao
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("zju.edu.friendlyarm.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                .description("Liver CT 接口测试文档")
                .contact(new Contact("xhzhao",
                        "https://github.com/zhaohandd",
                        "xhzhao@zju.edu.cn"))
                .version("v1.0")
                .title("API测试文档")
                .license("Apache2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build());
    }
}

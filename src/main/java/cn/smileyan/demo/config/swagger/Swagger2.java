package cn.smileyan.demo.config.swagger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Smileyan
 */
@EnableSwagger2
@Configurable
public class Swagger2 {
    /**
     * 特别要注意.apis(RequestHandlerSelectors.basePackage("cn.smileyan.swagger.controller"))
     * 此中的cn.smileyan.swagger.controller一定要修改为自己controller包。
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.smileyan.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("springboot使用swagger例子")
                .description("简单优雅的restful风格")
                .termsOfServiceUrl("https://smileyan.cn")
                .version("1.0")
                .build();
    }
}

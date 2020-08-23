package configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "controllers")
@PropertySource("classpath:web.properties")
public class WebConfig implements WebMvcConfigurer {

    @Value("${web.resource.path}")
    String resourcePath;
    @Value("${web.resource.location}")
    String resourcesLocation;
    @Value("${web.prefix}")
    String prefix;
    @Value("${web.suffix}")
    String suffix;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath).addResourceLocations("/pages/");
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(prefix);
        viewResolver.setSuffix(suffix);
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("security/login_page");
        registry.addViewController("/logout").setViewName("security/login_page");
        registry.addViewController("/access-denied").setViewName("security/access_denied_page");
        registry.addViewController("/home").setViewName("../../index");
        registry.addViewController("/").setViewName("../../index");

    }
}

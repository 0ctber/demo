package com.byd.demo.config.filter;

import com.byd.demo.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<MyFilter> createFilter(){
        FilterRegistrationBean<MyFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setEnabled(true);
        filterFilterRegistrationBean.setFilter(new MyFilter());
        filterFilterRegistrationBean.addUrlPatterns("/filter/*");
        return filterFilterRegistrationBean;
    }
}

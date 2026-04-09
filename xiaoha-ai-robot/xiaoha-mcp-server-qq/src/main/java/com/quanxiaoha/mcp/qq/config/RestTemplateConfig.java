package com.quanxiaoha.mcp.qq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/9 14:28
 * @Version: v1.0.0
 * @Description: RestTemplate 配置类
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10000);//连接超时时间： 10s
        factory.setReadTimeout(10000);//读取超时： 10s
        return new RestTemplate(factory);
    }
}

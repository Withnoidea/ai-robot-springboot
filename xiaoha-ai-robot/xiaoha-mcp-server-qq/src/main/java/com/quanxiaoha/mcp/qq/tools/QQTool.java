package com.quanxiaoha.mcp.qq.tools;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/9 14:31
 * @Version: v1.0.0
 * @Description:
 */
@Component
@Slf4j
public class QQTool {
    @Resource
    private RestTemplate restTemplate;

    @Value("${api-key}")
    private String apiKey;

    @Tool(description = "根据 QQ 号 获取 QQ 信息")
    public String getQQInfo(String qq) {
        log.info("获取QQ信息: {}", qq);
        //请求第三方接口
        String url = String.format("http://api.guiguiya.com/api/qq_info?apiKey=%s&qq=%s", apiKey, qq);
        String result = restTemplate.getForObject(url, String.class);

        log.info("## 返参: {}", result);
        return result;
    }

}

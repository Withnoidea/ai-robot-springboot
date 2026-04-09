package com.quanxiaoha.mcp.qq;

import com.quanxiaoha.mcp.qq.tools.QQTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/**
 * @Author: Withnoidea
 * @Date: 2026/4/9 14:31
 * @Version: v1.0.0
 * @Description:
 */
@SpringBootApplication
public class XiaohaMcpServerQqApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaohaMcpServerQqApplication.class, args);
    }

    /**
     * 注册工具回调（Tool Callbacks）
     * @param qqTool
     * @return
     */
    @Bean
    public ToolCallbackProvider qqTools(QQTool qqTool) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(qqTool)
                .build();
    }

}

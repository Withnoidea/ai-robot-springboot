package com.quanxiaoha.ai.robot.controller;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Author:
 * @Date: 2025/5/29 12:32
 * @Version: v1.0.0
 * @Description: 对接 Ollama 中的大模型
 **/
@RestController
@RequestMapping("/v3/ai")
public class OllamaController {

    @Resource
    private OllamaChatModel chatModel;

    /**
     * 普通对话
     * @param message
     * @return
     */
    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 构建提示词，调用大模型
        ChatResponse chatResponse = chatModel.call(new Prompt(
                message,
                OllamaOptions.builder()
                        .model("qwen3:14b") // 模型名称
                        .temperature(0.4) // 温度值
                        .build()
        ));

        // 响应回答内容
        return chatResponse.getResult().getOutput().getText();
    }

}
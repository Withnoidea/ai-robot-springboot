package com.quanxiaoha.vector.store.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/10 10:29
 * @Version: v1.0.0
 * @Description: RAG 增强检索
 */
@RestController
@RequestMapping("/rag")
public class RAGController {
    @Resource
    private VectorStore vectorStore;
    @Resource
    private ChatClient chatClient;

    /**
     * 流式对话
     * @param message
     * @return
     */
    @GetMapping(value = "/generateStream", produces = "text/html;charset=utf-8")
    public Flux<String> generateStream(@RequestParam(value = "message") String message) {

        // 流式输出
        return chatClient.prompt()
                .system("请你扮演一名企业客服。从企业内部知识库中查阅相关资料，并回答用户，若内部资料没有相关内容，则回答 “未找到相关资料”")
                .user(message) // 提示词
            .advisors(new QuestionAnswerAdvisor(vectorStore)) // 检索向量库，组合增强提示词，调用 AI 大模型
                .stream()
                .content();

    }
}

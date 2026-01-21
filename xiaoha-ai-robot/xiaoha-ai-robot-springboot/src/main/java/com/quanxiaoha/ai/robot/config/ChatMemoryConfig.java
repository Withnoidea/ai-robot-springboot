package com.quanxiaoha.ai.robot.config;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.cassandra.CassandraChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Withnoidea
 * @Date: 2025/5/27 14:42
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Configuration
public class ChatMemoryConfig {

    /**
     * 记忆存储
     */
//    @Resource
//    private ChatMemoryRepository chatMemoryRepository;

//    修改为注入 CassandraChatMemoryRepository , 即使用 Cassandra 来存储对话记录
    @Resource
    private CassandraChatMemoryRepository chatMemoryRepository;

    /**
     * 初始化一个 ChatMemory 实例，并注入到 Spring 容器中
     * @return
     */
    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(50) // 最大消息窗口为 50，默认值为 20
                .chatMemoryRepository(chatMemoryRepository) // 记忆存储
                .build();
    }
}

package com.quanxiaoha.vector.store.controller;

import com.quanxiaoha.vector.store.advisor.NetworkSearchAdvisor;
import com.quanxiaoha.vector.store.model.SearchResult;
import com.quanxiaoha.vector.store.service.SearXNGService;
import com.quanxiaoha.vector.store.service.SearchResultContentFetcherService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


/**
 * @Author: Withnoidea
 * @Date: 2026/4/10 10:53
 * @Version: v1.0.0
 * @Description: 联网搜索
 */
@RestController
@RequestMapping("/network")
public class NetworkSearchController {

    @Resource
    private SearXNGService searXNGService;

    @Resource
    private SearchResultContentFetcherService searchResultContentFetcherService;

    @Resource
    private ChatClient chatClient;

    /**
     * 测试
     * @param message
     * @return
     */
    @GetMapping(value = "/test")
    public List<SearchResult> generateStream(@RequestParam(value = "message") String message) {
        // 调用 SearXNG 获取搜索结果
        List<SearchResult> searchResults = searXNGService.search(message);

        // 并发请求，获取搜索结果页面的内容
        CompletableFuture<List<SearchResult>> resultsFuture = searchResultContentFetcherService.batchFetch(searchResults, 7, TimeUnit.SECONDS);

        List<SearchResult> searchResultList = resultsFuture.join();

        // TODO 后续处理

        return searchResultList;
    }

    /**
     * 流式对话
     * @param message
     * @return
     */
    @GetMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestParam(value = "message") String message) {

        // 流式输出
        return chatClient.prompt()
                .user(message) // 提示词
                .advisors(new NetworkSearchAdvisor(searXNGService, searchResultContentFetcherService)) // 使用自定义的联网搜索 Advisor
                .stream()
                .content();
    }

}


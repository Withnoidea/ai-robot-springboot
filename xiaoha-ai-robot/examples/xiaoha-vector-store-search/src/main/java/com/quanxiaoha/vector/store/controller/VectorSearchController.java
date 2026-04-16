package com.quanxiaoha.vector.store.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @Author: Withnoidea
 * @Date: 2026/4/9 16:49
 * @Version: v1.0.0
 * @Description:
 */
@RestController
@RequestMapping("/vector")
public class VectorSearchController {

    @Resource
    private VectorStore vectorStore;

    /**
     * 检索向量数据库
     * @param key
     * @return
     */
    @GetMapping(value = "/search")
    public List<Document> search(@RequestParam(value = "key") String key) {
        // 检索与查询相似的文档
        List<Document> results = vectorStore.similaritySearch(SearchRequest.builder()
                .query(key) // 查询的关键词
                .topK(2) // 查询相似度最高的 2 条文档
                .build());

        return results;
    }

}
package com.quanxiaoha.vector.store.service;
import com.quanxiaoha.vector.store.model.SearchResult;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
/**
 * @Author: Withnoidea
 * @Date: 2026/4/13 11:16
 * @Version: v1.0.0
 * @Description: 页面提取内容
 */
public interface SearchResultContentFetcherService {
    /**
     * 并发批量获取搜索结果页面的内容
     *
     * @param searchResults
     * @param timeout
     * @param unit
     * @return
     */
    CompletableFuture<List<SearchResult>> batchFetch(List<SearchResult> searchResults,
                                                     long timeout,
                                                     TimeUnit unit);
}

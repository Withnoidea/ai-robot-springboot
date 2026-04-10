package com.quanxiaoha.vector.store.service;

import com.quanxiaoha.vector.store.model.SearchResult;

import java.util.List;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/10 10:51
 * @Version: v1.0.0
 * @Description: TODO
 */
public interface  SearXNGService {
    /**
     * 调用 SearXNG Api, 获取搜索结果
     * @param query 搜索关键词
     * @return
     */
    List<SearchResult> search(String query);

}

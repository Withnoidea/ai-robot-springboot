package com.quanxiaoha.vector.store.reader;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.jsoup.JsoupDocumentReader;
import org.springframework.ai.reader.jsoup.config.JsoupDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/10 09:22
 * @Version: v1.0.0
 * @Description:
 */
@Component
public class MyHtmlReader {
    @Value("classpath:/document/my-page.html")
    private Resource resource;

    public List<Document> loadHtml(){
        //JsoupDocumentReader 阅读器配置类
        JsoupDocumentReaderConfig config = JsoupDocumentReaderConfig.builder()
                .selector("article p") // 提取 <article> 标签内的 p 段落
                .charset("utf-8") // 使用 UTF-8 编码
                .includeLinkUrls( true) // 在元数据中包含链接 URL（绝对链接）
                .metadataTags(List.of("author", "date")) // 提取 author 和 date 元标签
                .additionalMetadata("source", "my-page.html")
                .build();

        // 新建 JsoupDocumentReader 阅读器
        JsoupDocumentReader reader = new JsoupDocumentReader(resource, config);

        // 读取并转换为 Document 文档集合
        return reader.get();
    }
}

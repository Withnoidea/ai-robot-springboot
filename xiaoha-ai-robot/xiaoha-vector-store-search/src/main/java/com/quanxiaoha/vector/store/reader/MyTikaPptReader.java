package com.quanxiaoha.vector.store.reader;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/10 09:59
 * @Version: v1.0.0
 * @Description:
 */
@Component
public class MyTikaPptReader {
    @Value("classpath:/document/XX牌云感变频空调说明书.pptx")
    private Resource resource;

    public List<Document> loadPpt() {
        // 新建 TikaDocumentReader 阅读器
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(resource);
        // 读取并转换为 Document 文档集合
        List<Document> documents = tikaDocumentReader.get();

        // 文档分块
        // 使用自定义设置
        TokenTextSplitter splitter = new TokenTextSplitter(1000, 400, 10, 5000, true);
        return splitter.apply(documents);
    }
}
